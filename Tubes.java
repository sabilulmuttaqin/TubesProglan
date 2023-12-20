package com.example.tugasbesar;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;

public class Tubes extends Application {

    private double saldo;
    private TextField saldoTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Aplikasi Bank java GUI");

        // Membuat GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER); // Menengahkan GridPane

        // Label Selamat Datang
        Label welcomeLabel = new Label("Selamat Datang di");
        welcomeLabel.setFont(Font.font("Times New Roman", 16));
        welcomeLabel.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(welcomeLabel, 0, 0, 2, 1); // atas, kanan, bawah , kiri
        GridPane.setHalignment(welcomeLabel, HPos.CENTER);

        // Label  ATM Java GUI
        Label appNameLabel = new Label("ATM Java GUI");
        appNameLabel.setFont(Font.font("Times New Roman", 16));
        appNameLabel.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(appNameLabel, 0, 1, 2, 1); // atas, kanan, bawah , kiri
        GridPane.setHalignment(appNameLabel, HPos.CENTER);

        // Label dan TextField untuk Saldo
        Label saldoLabel = new Label("Saldo:");
        GridPane.setConstraints(saldoLabel, 0, 2, 2, 1);
        saldoTextField = new TextField();
        saldoTextField.setEditable(false);
        GridPane.setConstraints(saldoTextField, 0, 3, 2, 1);
        GridPane.setHalignment(saldoTextField, HPos.CENTER);

        // Button Cek Saldo
        Button cekSaldoButton = new Button("Cek Saldo");
        GridPane.setConstraints(cekSaldoButton, 0, 4, 1, 1);
        cekSaldoButton.setOnAction(e -> cekSaldo());
        GridPane.setHalignment(cekSaldoButton, HPos.CENTER);

        // Button Simpan
        Button simpanButton = new Button("Simpan");
        GridPane.setConstraints(simpanButton, 1, 4, 1, 1);
        simpanButton.setOnAction(e -> simpan());
        GridPane.setHalignment(simpanButton, HPos.CENTER);

        // Button Transfer
        Button transferButton = new Button("Transfer");
        GridPane.setConstraints(transferButton, 0, 5, 1, 1);
        transferButton.setOnAction(e -> transfer());
        GridPane.setHalignment(transferButton, HPos.CENTER);

        // Button Ambil
        Button ambilButton = new Button("Ambil");
        GridPane.setConstraints(ambilButton, 1, 5, 1, 1);
        ambilButton.setOnAction(e -> ambil());
        GridPane.setHalignment(ambilButton, HPos.CENTER);

        // Button Clear
        Button clearButton = new Button("Clear");
        GridPane.setConstraints(clearButton, 0, 6, 1, 1);
        clearButton.setOnAction(e -> clear());
        GridPane.setHalignment(clearButton, HPos.CENTER);

        // Button Keluar
        Button keluarButton = new Button("Keluar");
        GridPane.setConstraints(keluarButton, 1, 6, 1, 1);
        keluarButton.setOnAction(e -> keluar(primaryStage));
        GridPane.setHalignment(keluarButton, HPos.CENTER);

        // Menambahkan elemen-elemen ke GridPane
        gridPane.getChildren().addAll(
                welcomeLabel, appNameLabel,
                saldoLabel, saldoTextField,
                cekSaldoButton, simpanButton,
                transferButton, ambilButton,
                clearButton, keluarButton
        );

        // Membuat scene dan menetapkannya ke primaryStage
        Scene scene = new Scene(gridPane, 500, 600); // Menambahkan sedikit tinggi agar muat
        primaryStage.setScene(scene);

        // Menampilkan primaryStage
        primaryStage.show();
    }

    private void cekSaldo() {
        showAlert("Saldo Anda saat ini adalah: " + saldo);
    }

    private void simpan() {
        try {
            String jumlahSimpan = showAlertWithTextInput("Masukkan jumlah yang akan disimpan:");
            double masukan = Double.parseDouble(jumlahSimpan);

            if (masukan < 0) {
                showAlert("Jumlah yang dimasukkan tidak boleh negatif");
            } else {
                saldo += masukan;
                updateSaldoText();
            }
        } catch (NumberFormatException e) {
            showAlert("Input harus berupa angka");
        }
    }


    private void transfer() {
        try {
            String namaPenerima = showAlertWithTextInput("Masukkan nama penerima:");
            String jumlahTransfer = showAlertWithTextInput("Masukkan jumlah transfer:");
            double masukan = Double.parseDouble(jumlahTransfer);

            if (masukan < 0) {
                showAlert("Jumlah transfer tidak boleh negatif");
            } else if (saldo < masukan) {
                showAlert("Maaf, Saldo Anda tidak mencukupi untuk transfer ini");
            } else {
                saldo -= masukan;
                showAlert("Transfer berhasil kepada " + namaPenerima + " sebesar " + masukan);
                updateSaldoText();
            }
        } catch (NumberFormatException e) {
            showAlert("Input harus berupa angka");
        }
    }


    private void ambil() {
        try {
            String jumlahAmbil = showAlertWithTextInput("Masukkan jumlah yang akan diambil:");
            double masukan = Double.parseDouble(jumlahAmbil);

            if (masukan < 0) {
                showAlert("Jumlah penarikan tidak boleh negatif");
            } else if (saldo < masukan) {
                showAlert("Maaf, Saldo Anda tidak mencukupi untuk penarikan ini");
            } else {
                saldo -= masukan;
                showAlert("Penarikan berhasil sebesar " + masukan);
                updateSaldoText();
            }
        } catch (NumberFormatException e) {
            showAlert("Input harus berupa angka");
        }
    }



    private void clear() {
        saldoTextField.clear();
    }

    private void keluar(Stage primaryStage) {
        primaryStage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String showAlertWithTextInput(String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input");
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    private void updateSaldoText() {
        saldoTextField.setText(String.format("Rp %.2f", saldo));
    }


}

