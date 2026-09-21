package minibank3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<Rekening>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cetak Mutasi");
			System.out.println("0. Keluar");
			System.out.println("Pilih Menu");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch (pilihan) {
			case 1:
				System.out.print("Masukkan No Rekening: ");
				String no = input.nextLine();
				System.out.print("Masukkan Nama Pemilik: ");
				String nama = input.nextLine();
				
				String pin = "";
				while (true) {
					System.out.print("Masukkan PIN: ");
					pin = input.nextLine();
					if (pin.matches("\\d{6}")) {
						break;
					} else {
						System.out.println("Error: PIN harus berupa 6 digit angka!");
					}
					
				}
				
				System.out.print("Masukkan Saldo Awal: ");
				double saldo = input.nextDouble();
				input.nextLine();
				
				Rekening rekeningBaru = new Rekening(no, nama, saldo, pin);
				daftarRekening.add(rekeningBaru);
				akunAktif = rekeningBaru;
				System.out.println("Rekening berhasi dibuat!");
				break;
				
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukkan nominal setor: Rp");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
				
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukkan nominal tarik: Rp");
					double tarik = input.nextDouble();
					input.nextLine();
					
					System.out.print("Masukkan PIN: ");
					String pinInput = input.nextLine();
					if (akunAktif.otentikasi(pinInput)) {
						akunAktif.tarikTunai(tarik);
					} else {
						System.out.println("Akses Ditolak: PIN yang anda masukkan salah!");
					}
				}
				break;
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if(daftarRekening.isEmpty()) {
					System.out.println("Error: Belum ada rekening!");
				} else {
					System.out.print("Masukkan No Rekening: ");
					String noRekening = input.nextLine();
					boolean ditemukan = false;
					for (Rekening rekening : daftarRekening) {
						if(rekening.getNomorRekening().equals(noRekening)) {
							akunAktif = rekening;
							ditemukan = true;
							System.out.println("Akun berhasil diganti.");
							break;
						}
					}
					if (!ditemukan) {
						System.out.println("Rekening tidak ditemukan!");
					}
				}
				break;
				
			case 6:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					System.out.print("Masukkan PIN: ");
					String pinInput = input.nextLine();
					
					if (akunAktif.otentikasi(pinInput)) {
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses Ditolak: PIN yang anda masukkan salah!");
					}
				}
				break;
				
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima Kasih!");
				break;
				
			default:
				System.out.println("Pilihan tidak valid!");
			}
					
		}
		input.close();
	}

}