import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
abstract class SanPham {
    protected String maSanPham;
    protected String tenSanPham;
    protected double gia;
    protected int soLuong;
    protected Date hanSuDung;

    public SanPham(String maSanPham, String tenSanPham, double gia, int soLuong, Date hanSuDung) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hanSuDung = hanSuDung;
    }
	public String getMaSanPham(){
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham){
		this.maSanPham=maSanPham;
	}
	public String getTenSanPham(){
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham){
		this.tenSanPham=tenSanPham;
	}
	public double getGia(){
		return gia;
	}
	public void setGia(double gia){
		this.gia=gia;
	}
	public int getSoLuong(){
		return soLuong;
	}
	public void setSoLuong(int soLuong){
		this.soLuong=soLuong;
	}
	public Date getHanSuDung(){
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung){
		this.hanSuDung=hanSuDung;
	}
	abstract double tongTien();
	abstract String loaiSP();
	abstract void hienThi();
	final public boolean kTHetHan(){
		Date n=new Date();

    	if (n.compareTo(this.hanSuDung)>0){
    		return true;
    	}
    	else{
    		return false;
    	}
	}
}
class Sua extends SanPham{
	public Sua(String maSanPham, String tenSanPham, double gia, int soLuong, Date hanSuDung){
		super(maSanPham, tenSanPham, gia, soLuong, hanSuDung);
	}
	public double tongTien(){
		double tong=0;
		if (getSoLuong()>=500){
			tong=(getSoLuong()*getGia())-(getSoLuong()*getGia())*0.1;
		}else if(getSoLuong()>=1000){
			tong=(getSoLuong()*getGia())-(getSoLuong()*getGia())*0.2;
		}else{
			tong=(getSoLuong()*getGia());
		}
		return tong;
	}
	public String loaiSP(){
		return "San pham Sua";
	}
	public void hienThi(){
	System.out.println ("Ma SP: "+maSanPham);
		System.out.println ("Ten SP: "+tenSanPham);
		System.out.println ("Gia: "+gia);
		System.out.println ("So luong:" + soLuong);
		SimpleDateFormat fm= new SimpleDateFormat("dd/MM/yyyy");
		System.out.println ("HSD: "+fm.format(this.hanSuDung));
		System.out.println ("Tong tien:" + tongTien()+"\n");}
}
class SPLenMen extends SanPham{
	public SPLenMen(String maSanPham, String tenSanPham, double gia, int soLuong, Date hanSuDung){
		super(maSanPham, tenSanPham, gia, soLuong, hanSuDung);
	}
	public double tongTien(){
		double tong=0;
		if (getSoLuong()>=200){
			tong=(getSoLuong()*getGia())-(getSoLuong()*getGia())*0.1;
		}else if(getSoLuong()>=500){
			tong=(getSoLuong()*getGia())-(getSoLuong()*getGia())*0.15;
		}else{
			tong=(getSoLuong()*getGia());
		}
		return tong;
	}
	public String loaiSP(){
		return "San pham len men";
	}
	public void hienThi(){
	System.out.println ("Ma SP: "+maSanPham);
		System.out.println ("Ten SP: "+tenSanPham);
		System.out.println ("Gia: "+gia);
		System.out.println ("So luong:" + soLuong);
		SimpleDateFormat fm= new SimpleDateFormat("dd/MM/yyyy");
		System.out.println ("HSD: "+fm.format(this.hanSuDung));
		System.out.println ("Tong tien:" + tongTien()+"\n");

			 }
}
class DSSanPham{
	private SanPham ds[];
	private int soSanPhamDaBan;
	public DSSanPham(int n){
		ds=new SanPham[n];
		soSanPhamDaBan=0;
	}
	public void them(SanPham a){
		if(soSanPhamDaBan<ds.length){
			ds[soSanPhamDaBan++]=a;
		}
	}
	public void lietKe(){
		for(int i=0;i<soSanPhamDaBan;i++){
			ds[i].hienThi();
		}
	}
	public void lietKe(String loaiSP){
		for(int i=0;i<soSanPhamDaBan;i++){
			if(ds[i].loaiSP().equals(loaiSP)){
				ds[i].hienThi();
			}
		}
	}
	public double tongDanhThu(){
		double s=0;
		for(int i=0;i<soSanPhamDaBan;i++){
			s+=ds[i].tongTien();
		}
		return s;
	}
	public double tongDanhThu(String loaiSP){
		double s=0;
		for(int i=0;i<soSanPhamDaBan;i++){
			if(ds[i].loaiSP().equals(loaiSP)){
				s+=ds[i].tongTien();
			}
		}
		return s;
	}
	public void lietKeSPHetHan(){
		boolean kt=true;
		for(int i=0;i<soSanPhamDaBan;i++){
			if(ds[i].kTHetHan())
				ds[i].hienThi();

		}
	}

	public void lietKeSPHetHan(String loaiSP){
		boolean kt=true;
		for(int i=0;i<soSanPhamDaBan;i++){
			if(ds[i].loaiSP().equals(loaiSP))
				if(ds[i].kTHetHan())
					ds[i].hienThi();

		}
	}

	public static void main (String[] args) {
		DSSanPham ds = new DSSanPham(5);
		ds.them(new Sua("112","sua",10000,2,new Date("1/1/2022")));
		ds.them(new SPLenMen("011","SPLM1", 2000,5,new Date("1/1/2025") ));
		ds.them(new SPLenMen("012","SPLM2", 1000,10,new Date("10/1/2020") ));
		System.out.println("-----DANH SACH-----");
		ds.lietKe();
		System.out.println("-----DANH SACH SP SUA-----");
		ds.lietKe("San pham Sua");
		System.out.printf("-----TONG DOANH THU SPLM: %.2f\n",ds.tongDanhThu("San pham len men"));
    	System.out.println("-----DANH SACH SPLM HET HAN-----");
		ds.lietKeSPHetHan("San pham len men");
    }
}