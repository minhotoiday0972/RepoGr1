import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String getMaSanPham() { return maSanPham; }
    public String getTenSanPham() { return tenSanPham; }
    public double getGia() { return gia; }
    public int getSoLuong() { return soLuong; }
    public Date getHanSuDung() { return hanSuDung; }

    public void setGia(double gia) { this.gia = gia; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public boolean kTHetHan() {
        return new Date().after(hanSuDung);
    }

    abstract double tongTien();
    abstract String loaiSP();
    abstract void hienThi();
}

class Sua extends SanPham {
    public Sua(String maSanPham, String tenSanPham, double gia, int soLuong, Date hanSuDung) {
        super(maSanPham, tenSanPham, gia, soLuong, hanSuDung);
    }

    @Override
    public double tongTien() {
        if (soLuong >= 1000) return soLuong * gia * 0.8;
        if (soLuong >= 500) return soLuong * gia * 0.9;
        return soLuong * gia;
    }

    @Override
    public String loaiSP() {
        return "San pham Sua";
    }

    @Override
    public void hienThi() {
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("Ma SP: %s\nTen SP: %s\nGia: %.2f\nSo luong: %d\nHSD: %s\nTong tien: %.2f\n\n",
                          maSanPham, tenSanPham, gia, soLuong, fm.format(hanSuDung), tongTien());
    }
}

class SPLenMen extends SanPham {
    public SPLenMen(String maSanPham, String tenSanPham, double gia, int soLuong, Date hanSuDung) {
        super(maSanPham, tenSanPham, gia, soLuong, hanSuDung);
    }

    @Override
    public double tongTien() {
        if (soLuong >= 500) return soLuong * gia * 0.85;
        if (soLuong >= 200) return soLuong * gia * 0.9;
        return soLuong * gia;
    }

    @Override
    public String loaiSP() {
        return "San pham len men";
    }

    @Override
    public void hienThi() {
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("Ma SP: %s\nTen SP: %s\nGia: %.2f\nSo luong: %d\nHSD: %s\nTong tien: %.2f\n\n",
                          maSanPham, tenSanPham, gia, soLuong, fm.format(hanSuDung), tongTien());
    }
}

class DSSanPham {
    private final SanPham[] ds;
    private int soSanPhamDaBan;

    public DSSanPham(int n) {
        ds = new SanPham[n];
        soSanPhamDaBan = 0;
    }

    public void them(SanPham sp) {
        if (soSanPhamDaBan < ds.length) {
            ds[soSanPhamDaBan++] = sp;
        }
    }

    public void lietKe() {
        for (int i = 0; i < soSanPhamDaBan; i++) {
            ds[i].hienThi();
        }
    }

    public void lietKe(String loaiSP) {
        for (int i = 0; i < soSanPhamDaBan; i++) {
            if (ds[i].loaiSP().equals(loaiSP)) {
                ds[i].hienThi();
            }
        }
    }

    public double tongDanhThu() {
        double tong = 0;
        for (int i = 0; i < soSanPhamDaBan; i++) {
            tong += ds[i].tongTien();
        }
        return tong;
    }

    public double tongDanhThu(String loaiSP) {
        double tong = 0;
        for (int i = 0; i < soSanPhamDaBan; i++) {
            if (ds[i].loaiSP().equals(loaiSP)) {
                tong += ds[i].tongTien();
            }
        }
        return tong;
    }

    public void lietKeSPHetHan() {
        for (int i = 0; i < soSanPhamDaBan; i++) {
            if (ds[i].kTHetHan()) {
                ds[i].hienThi();
            }
        }
    }

    public void lietKeSPHetHan(String loaiSP) {
        for (int i = 0; i < soSanPhamDaBan; i++) {
            if (ds[i].loaiSP().equals(loaiSP) && ds[i].kTHetHan()) {
                ds[i].hienThi();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        DSSanPham ds = new DSSanPham(5);
        ds.them(new Sua("112", "Sua", 10000, 2, sdf.parse("01/01/2022")));
        ds.them(new SPLenMen("011", "SPLM1", 2000, 5, sdf.parse("01/01/2025")));
        ds.them(new SPLenMen("012", "SPLM2", 1000, 10, sdf.parse("10/01/2020")));

        System.out.println("----- DANH SACH -----");
        ds.lietKe();

        System.out.println("----- DANH SACH SP SUA -----");
        ds.lietKe("San pham Sua");

        System.out.printf("----- TONG DOANH THU SPLM: %.2f\n", ds.tongDanhThu("San pham len men"));

        System.out.println("----- DANH SACH SPLM HET HAN -----");
        ds.lietKeSPHetHan("San pham len men");
    }
}
