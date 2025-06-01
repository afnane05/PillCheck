package com.pillcheck.medicalapp.Controller.Parametre;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.pillcheck.medicalapp.Model.PatientModels.Patient;
import com.pillcheck.medicalapp.Model.PatientModels.PatientDAO;
import com.pillcheck.medicalapp.Model.RdvModels.Rdv;
import com.pillcheck.medicalapp.Model.RdvModels.RdvDAO;
import com.pillcheck.medicalapp.Model.TraitementModels.Traitement;
import com.pillcheck.medicalapp.Model.TraitementModels.TraitementDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class ExporterController {

    @FXML
    private AnchorPane rootPane;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private File chooseFile(String desc, String ext) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(desc, ext));
        fileChooser.setInitialFileName("export" + ext.substring(1)); // e.g., .pdf → export.pdf
        return fileChooser.showSaveDialog(rootPane.getScene().getWindow());
    }

    @FXML
    private void exportCSV() {
        File file = chooseFile("Fichiers CSV (*.csv)", "*.csv");
        if (file == null) return;

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            List<Patient> patients = PatientDAO.getAllPatients();
            List<Rdv> rdvs = RdvDAO.getAllRdv();
            List<Traitement> traitements = TraitementDao.getAllTraitements();

            writer.write("----- Patients -----\n");
            writer.write("CIN,Nom,Prénom,Date Naissance,Téléphone,État,Sexe,Nom Traitement\n");
            for (Patient p : patients) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s\n",
                        p.getCin(), p.getNom(), p.getPrenom(),
                        p.getDateNaissance() != null ? p.getDateNaissance().format(dateFormatter) : "",
                        p.getTelephone(), p.getEtat(), p.getSexe(), p.getNomTraitement()));
            }

            writer.write("\n----- Rendez-vous -----\n");
            writer.write("ID,Date Heure,Motif,Statut,CIN Patient\n");
            for (Rdv r : rdvs) {
                writer.write(String.format("%d,%s,%s,%s\n",
                        r.getIdRdv(),
                        r.getDateRdv() != null ? r.getDateRdv().format(dateTimeFormatter) : "",
                        r.getMotifRdv(), r.getStatut()));
            }

            writer.write("\n----- Traitements -----\n");
            writer.write("Nom,Type,Maladie,Description,Début,Fin,Durée,Posologie,Effets,État\n");
            for (Traitement t : traitements) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                        t.getNOM_TRAITEMENT(), t.getTYPE_TRAITEMENT(), t.getMALADIE(), t.getDESCRIPTION(),
                        t.getDATE_DEBUT() != null ? t.getDATE_DEBUT().format(dateFormatter) : "",
                        t.getDATE_FIN() != null ? t.getDATE_FIN().format(dateFormatter) : "",
                        t.getDUREE_ESTIMEE(), t.getPOSOLOGIE(),
                        t.getEFFETS_SECONDAIRES(), t.getETAT()));
            }

            showAlert("Succès", "Export CSV réussi !");
        } catch (IOException e) {
            showAlert("Erreur", "Erreur export CSV : " + e.getMessage());
        }
    }

    @FXML
    private void exportPDF() {
        File file = chooseFile("Fichiers PDF (*.pdf)", "*.pdf");
        if (file == null) return;

        try {
            List<Patient> patients = PatientDAO.getAllPatients();
            List<Rdv> rdvs = RdvDAO.getAllRdv();
            List<Traitement> traitements = TraitementDao.getAllTraitements();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("Export des données PillCheck", titleFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Patients", sectionFont));
            for (Patient p : patients) {
                document.add(new Paragraph(String.format("CIN: %s, Nom: %s %s, Naissance: %s, Téléphone: %s, État: %s, Sexe: %s, Traitement: %s",
                        p.getCin(), p.getNom(), p.getPrenom(),
                        p.getDateNaissance() != null ? p.getDateNaissance().format(dateFormatter) : "N/A",
                        p.getTelephone(), p.getEtat(), p.getSexe(), p.getNomTraitement()), normalFont));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Rendez-vous", sectionFont));
            for (Rdv r : rdvs) {
                document.add(new Paragraph(String.format("ID: %d, Date: %s, Motif: %s, Statut: %s",
                        r.getIdRdv(),
                        r.getDateRdv() != null ? r.getDateRdv().format(dateTimeFormatter) : "N/A",
                        r.getMotifRdv(), r.getStatut()), normalFont));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Traitements", sectionFont));
            for (Traitement t : traitements) {
                document.add(new Paragraph(String.format("Nom: %s, Type: %s, Maladie: %s, Description: %s, Début: %s, Fin: %s, Durée: %s, Posologie: %s, Effets: %s, État: %s",
                        t.getNOM_TRAITEMENT(), t.getTYPE_TRAITEMENT(), t.getMALADIE(), t.getDESCRIPTION(),
                        t.getDATE_DEBUT() != null ? t.getDATE_DEBUT().format(dateFormatter) : "N/A",
                        t.getDATE_FIN() != null ? t.getDATE_FIN().format(dateFormatter) : "N/A",
                        t.getDUREE_ESTIMEE(), t.getPOSOLOGIE(),
                        t.getEFFETS_SECONDAIRES(), t.getETAT()), normalFont));
            }

            document.close();
            showAlert("Succès", "Export PDF réussi !");
        } catch (Exception e) {
            showAlert("Erreur", "Erreur export PDF : " + e.getMessage());
        }
    }

    @FXML
    private void exportExcel() {
        File file = chooseFile("Fichiers Excel (*.xlsx)", "*.xlsx");
        if (file == null) return;

        try (Workbook workbook = new XSSFWorkbook()) {
            List<Patient> patients = PatientDAO.getAllPatients();
            List<Rdv> rdvs = RdvDAO.getAllRdv();
            List<Traitement> traitements = TraitementDao.getAllTraitements();

            // Patients
            Sheet patientSheet = workbook.createSheet("Patients");
            String[] pHeaders = {"CIN","Nom","Prénom","Naissance","Téléphone","État","Sexe","Traitement"};
            Row header = patientSheet.createRow(0);
            for (int i = 0; i < pHeaders.length; i++) header.createCell(i).setCellValue(pHeaders[i]);

            int rowNum = 1;
            for (Patient p : patients) {
                Row row = patientSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(p.getCin());
                row.createCell(1).setCellValue(p.getNom());
                row.createCell(2).setCellValue(p.getPrenom());
                row.createCell(3).setCellValue(p.getDateNaissance() != null ? p.getDateNaissance().toString() : "");
                row.createCell(4).setCellValue(p.getTelephone());
                row.createCell(5).setCellValue(p.getEtat());
                row.createCell(6).setCellValue(p.getSexe());
                row.createCell(7).setCellValue(p.getNomTraitement());
            }

            // Rdvs
            Sheet rdvSheet = workbook.createSheet("Rendez-vous");
            String[] rHeaders = {"ID","Date","Motif","Statut","CIN Patient"};
            Row rHeader = rdvSheet.createRow(0);
            for (int i = 0; i < rHeaders.length; i++) rHeader.createCell(i).setCellValue(rHeaders[i]);

            rowNum = 1;
            for (Rdv r : rdvs) {
                Row row = rdvSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(r.getIdRdv());
                row.createCell(1).setCellValue(r.getDateRdv() != null ? r.getDateRdv().toString() : "");
                row.createCell(2).setCellValue(r.getMotifRdv());
                row.createCell(3).setCellValue(r.getStatut());
            }

            // Traitements
            Sheet tSheet = workbook.createSheet("Traitements");
            String[] tHeaders = {"Nom","Type","Maladie","Description","Début","Fin","Durée","Posologie","Effets","État"};
            Row tHeader = tSheet.createRow(0);
            for (int i = 0; i < tHeaders.length; i++) tHeader.createCell(i).setCellValue(tHeaders[i]);

            rowNum = 1;
            for (Traitement t : traitements) {
                Row row = tSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(t.getNOM_TRAITEMENT());
                row.createCell(1).setCellValue(t.getTYPE_TRAITEMENT());
                row.createCell(2).setCellValue(t.getMALADIE());
                row.createCell(3).setCellValue(t.getDESCRIPTION());
                row.createCell(4).setCellValue(t.getDATE_DEBUT() != null ? t.getDATE_DEBUT().toString() : "");
                row.createCell(5).setCellValue(t.getDATE_FIN() != null ? t.getDATE_FIN().toString() : "");
                row.createCell(6).setCellValue(t.getDUREE_ESTIMEE());
                row.createCell(7).setCellValue(t.getPOSOLOGIE());
                row.createCell(8).setCellValue(t.getEFFETS_SECONDAIRES());
                row.createCell(9).setCellValue(t.getETAT());
            }

            // Autosize
            for (Sheet sheet : List.of(patientSheet, rdvSheet, tSheet)) {
                Row h = sheet.getRow(0);
                if (h != null) {
                    for (int i = 0; i < h.getPhysicalNumberOfCells(); i++) {
                        sheet.autoSizeColumn(i);
                    }
                }
            }

            try (FileOutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            }

            showAlert("Succès", "Export Excel réussi !");
        } catch (Exception e) {
            showAlert("Erreur", "Erreur export Excel : " + e.getMessage());
        }
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
