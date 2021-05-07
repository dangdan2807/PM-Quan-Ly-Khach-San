package baiTapCuoiKy;

import java.util.ArrayList;

public class HoaDonDV {
	private ArrayList<DichVu> dsDV;

	public HoaDonDV() {
		dsDV = new ArrayList<DichVu>();

	}

	public boolean themDV(DichVu d) {
		if (dsDV.contains(d))
			return false;
		dsDV.add(d);
		return true;
	}

	public boolean xoaDV(String maDV) {
		DichVu d = new DichVu(maDV);
		if (dsDV.contains(d)) {
			dsDV.remove(dsDV.get(dsDV.indexOf(d)));
			return true;
		}
		return false;

	}

	public DichVu timDV(String maDV) {
		DichVu d = new DichVu(maDV);
		if (dsDV.contains(d))
			return dsDV.get(dsDV.indexOf(d));
		return null;
	}
	public ArrayList<DichVu> getDSDV() {
		return dsDV;
	}
	
}
