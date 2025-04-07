/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan_bchub;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Lab
 */
public class FrmPeminjaman extends javax.swing.JFrame {

    /**
     * Creates new form FrmPeminjaman
     */
    public FrmPeminjaman() {
        initComponents();
        loadDataToGrid();
        readDataFromTableAnggota();
        readDataFromTableBuku();
        IdOtomatis();
    }

public void loadDataToGrid() {
    Connection connection = null;
    CallableStatement cstmt = null;
    ResultSet rs = null;

    try {
        connection = koneksi.getConnection();
        Object header[] = {"ID Peminjaman", "Anggota ID", "Nama", "Buku ID", "Judul", "Tgl Peminjaman", "Tgl Pengembalian"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        tabelPeminjaman.setModel(data);

        // Memanggil prosedur
        cstmt = connection.prepareCall("{call ambil_semua_peminjaman(?)}");
        cstmt.registerOutParameter(1, OracleTypes.CURSOR); // Mendaftarkan parameter output sebagai cursor

        // Menjalankan prosedur
        cstmt.execute();

        // Mengambil hasil dari cursor
        rs = (ResultSet) cstmt.getObject(1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format tanggal yang diinginkan

        while (rs.next()) {
            // Mengambil data dengan tipe yang sesuai
            String d1 = rs.getString("ID_PEMINJAMAN"); // Menggunakan getInt untuk tipe NUMBER
            String d2 = rs.getString("ANGGOTA_ID"); // Menggunakan getInt untuk tipe NUMBER
            String d3 = rs.getString("NAMA_ANGGOTA"); // Mengambil nama anggota
            String d4 = rs.getString("BUKU_ID"); // Menggunakan getInt untuk tipe NUMBER
            String d5 = rs.getString("JUDUL_BUKU"); // Mengambil judul buku
            // Mengambil tanggal dan mengonversinya ke String
            String d6 = dateFormat.format(rs.getDate("TANGGAL_PEMINJAMAN")); // Tgl Peminjaman
            String d7 = dateFormat.format(rs.getDate("TANGGAL_PENGEMBALIAN")); // Tgl Pengembalian

            // Mengonversi d1, d2, d4 ke String untuk ditampilkan di JTable
            String[] d = {String.valueOf(d1), d2, d3, d4, d5, d6, d7}; // Menambahkan d7 ke array
            data.addRow(d);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        // Menutup ResultSet dan CallableStatement
        try {
            if (rs != null) rs.close();
            if (cstmt != null) cstmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
   
public void IdOtomatis(){
        Connection connection = null;
        try{
            connection = koneksi.getConnection();
            Statement st= connection.createStatement();
            String sql_id="SELECT * FROM peminjaman order by id_peminjaman desc";
            ResultSet rs=st.executeQuery(sql_id);
            if(rs.next())
            {
                String id_peminjaman=rs.getString("id_peminjaman").substring(1);
                String AN=""+(Integer.parseInt(id_peminjaman)+1);
                String No1="";
                if (AN.length()==1)
                {
                    No1="000";
                } else if (AN.length()==2)
                {
                    No1="00";
                } else if (AN.length()==3)
                {
                    No1="0";
                }
                txtIdPeminjaman.setText("P"+No1+AN);
            }else 
            {
                txtIdPeminjaman.setText("P0001");
            }
          }catch(Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
    }

    public void readDataFromTableAnggota() {
        String query;
        query = "SELECT anggota_id FROM anggota";
        
        try(Connection connection = koneksi.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            cbxAnggotId.removeAllItems();
            while (resultSet.next()) {
                cbxAnggotId.addItem(resultSet.getString("anggota_id"));
            }
        } catch (SQLException e) {
        }
    }
    
        public void readDataFromTableBuku() {
        String query;
        query = "SELECT buku_id FROM buku";
        
        try(Connection connection = koneksi.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            cbxBukuId.removeAllItems();
            while (resultSet.next()) {
                cbxBukuId.addItem(resultSet.getString("buku_id"));
            }
        } catch (SQLException e) {
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdPeminjaman = new javax.swing.JTextField();
        txtJudulBuku = new javax.swing.JTextField();
        btnCariAnggota = new javax.swing.JButton();
        btnCariBuku = new javax.swing.JButton();
        inputPeminjaman = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPeminjaman = new javax.swing.JTable();
        cbxAnggotId = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxBukuId = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNamaAnggota = new javax.swing.JTextField();
        kembali = new javax.swing.JButton();
        tglPeminjaman = new com.toedter.calendar.JDateChooser();
        tglPengembalian = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PEMINJAMAN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID PEMINJAMAN");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ANGGOTA ID");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUKU ID");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TANGGAL PEMINJAMAN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TANGGAL PENGEMBALIAN");

        btnCariAnggota.setText("CARI");
        btnCariAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariAnggotaActionPerformed(evt);
            }
        });

        btnCariBuku.setText("CARI");
        btnCariBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBukuActionPerformed(evt);
            }
        });

        inputPeminjaman.setText("Input");
        inputPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPeminjamanActionPerformed(evt);
            }
        });

        tabelPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelPeminjaman);

        cbxAnggotId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("NAMA ANGGOTA");

        cbxBukuId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("JUDUL BUKU");

        kembali.setText("Kembali");
        kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(inputPeminjaman))
                            .addComponent(txtIdPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cbxAnggotId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addComponent(btnCariAnggota))
                                .addComponent(txtJudulBuku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cbxBukuId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCariBuku)))
                            .addComponent(txtNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tglPeminjaman, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(tglPengembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(339, 339, 339))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(kembali)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIdPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxAnggotId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariAnggota))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxBukuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariBuku))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(tglPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tglPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(24, 24, 24)
                        .addComponent(inputPeminjaman))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(kembali)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembaliActionPerformed
        // TODO add your handling code here:
             int keluar;
            keluar = JOptionPane.showConfirmDialog(this,
            "Keluar dari Data Peminjaman?",
            "Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(keluar == JOptionPane.YES_OPTION)
        {
                new FrmPustakawan().show();
                this.dispose();
        }
    }//GEN-LAST:event_kembaliActionPerformed

    private void inputPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPeminjamanActionPerformed
                                                
    // Mengisi nilai untuk parameter stored procedure
    String p_peminjaman_id = txtIdPeminjaman.getText(); // Mengambil ID Peminjaman sebagai String

    // Validasi apakah ID Peminjaman kosong
    if (p_peminjaman_id == null || p_peminjaman_id.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "ID Peminjaman tidak boleh kosong!");
        return; // Keluar dari metode jika ID kosong
    }

    // Mengambil data dari combo box dan JDateChooser
    String p_anggota_id = cbxAnggotId.getSelectedItem().toString(); // Mengonversi ke String
    String p_buku_id = cbxBukuId.getSelectedItem().toString(); // Mengonversi ke String

    // Mengambil tanggal dari JDateChooser
    java.util.Date utilDatePeminjaman = tglPeminjaman.getDate();
    java.util.Date utilDatePengembalian = tglPengembalian.getDate();

    // Memeriksa apakah tanggal peminjaman dan pengembalian tidak null
    if (utilDatePeminjaman == null || utilDatePengembalian == null) {
        JOptionPane.showMessageDialog(this, "Silakan pilih tanggal peminjaman dan pengembalian!");
        return; // Keluar dari metode jika tanggal tidak dipilih
    }

    // Mengonversi java.util.Date ke java.sql.Date
    java.sql.Date sqlDatePeminjaman = new java.sql.Date(utilDatePeminjaman.getTime());
    java.sql.Date sqlDatePengembalian = new java.sql.Date(utilDatePengembalian.getTime());

    try (Connection connection = koneksi.getConnection()) {
        // Panggil stored procedure
        String storedProcedureCall = "{call insert_and_display_peminjaman(?, ?, ?, ?, ?)}"; // Menggunakan kurung kurawal
        try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
            // Set parameter stored procedure
            callableStatement.setString(1, p_peminjaman_id); // Parameter 1: ID Peminjaman (sekarang String)
            callableStatement.setString(2, p_anggota_id); // Parameter 2: ID Anggota
            callableStatement.setString(3, p_buku_id); // Parameter 3: ID Buku
            callableStatement.setDate(4, sqlDatePeminjaman); // Parameter 4: Tanggal Peminjaman
            callableStatement.setDate(5, sqlDatePengembalian); // Parameter 5: Tanggal Pengembalian

            // Eksekusi stored procedure
            callableStatement.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil di Input!!");

            // Mengosongkan field input
            txtIdPeminjaman.setText(""); // Mengosongkan field ID Peminjaman
            cbxAnggotId.setSelectedIndex(-1); // Mengosongkan combo box Anggota ID
            cbxBukuId.setSelectedIndex(-1); // Mengosongkan combo box Buku ID
            tglPeminjaman.setDate(null); // Mengosongkan JDateChooser Tanggal Peminjaman
            tglPengembalian.setDate(null); // Mengosongkan JDateChooser Tanggal Pengembalian

            loadDataToGrid(); // Memanggil metode untuk memuat data peminjaman
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Data Gagal di Input!!");
        e.printStackTrace();
    }
    }//GEN-LAST:event_inputPeminjamanActionPerformed

    private void btnCariAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariAnggotaActionPerformed
        // TODO add your handling code here:
        String p_anggota_id = cbxAnggotId.getSelectedItem().toString();
        String query = "SELECT nama FROM anggota WHERE anggota_id=?";
        
        try {Connection connection = koneksi.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, p_anggota_id);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()==true) {
                    txtNamaAnggota.setText(resultSet.getString(1));
                }
            
                
            } 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal ditampilkan");;
        }
    }//GEN-LAST:event_btnCariAnggotaActionPerformed

    private void btnCariBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBukuActionPerformed
        // TODO add your handling code here:
        String p_buku_id = cbxBukuId.getSelectedItem().toString();
        String query = "SELECT judul FROM buku WHERE buku_id=?";
        
        try {Connection connection = koneksi.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, p_buku_id);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()==true) {
                    txtJudulBuku.setText(resultSet.getString(1));
                }
            
                
            } 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal ditampilkan");;
        }
    }//GEN-LAST:event_btnCariBukuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariAnggota;
    private javax.swing.JButton btnCariBuku;
    private javax.swing.JComboBox<String> cbxAnggotId;
    private javax.swing.JComboBox<String> cbxBukuId;
    private javax.swing.JButton inputPeminjaman;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kembali;
    private javax.swing.JTable tabelPeminjaman;
    private com.toedter.calendar.JDateChooser tglPeminjaman;
    private com.toedter.calendar.JDateChooser tglPengembalian;
    private javax.swing.JTextField txtIdPeminjaman;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtNamaAnggota;
    // End of variables declaration//GEN-END:variables
}
