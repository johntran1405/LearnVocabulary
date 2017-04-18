package ccwav.blogspot.com.learnvocabulary.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import ccwav.blogspot.com.learnvocabulary.Model.bainghedbModel;
import ccwav.blogspot.com.learnvocabulary.Model.chudedbModel;
import ccwav.blogspot.com.learnvocabulary.Model.hinhanhdbModel;
import ccwav.blogspot.com.learnvocabulary.Model.maucaudbModel;
import ccwav.blogspot.com.learnvocabulary.Model.nhandangtudbModel;
import ccwav.blogspot.com.learnvocabulary.Model.trochoidbModel;


public class SQLiteContactController extends SQLiteDataController {
    public SQLiteContactController(Context con) {
        super(con);
    }

    public ArrayList<bainghedbModel> getListBaiNghe() {
        ArrayList<bainghedbModel> listBaiNghe = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from bainghedb order by id asc", null);

            bainghedbModel ct;
            while (cs.moveToNext()) {
//                public bainghedbModel(int id, String name, String audio, String question, String answer1, String answer2, String answer3, String answer4) {
                    ct = new bainghedbModel(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3), cs.getString(4), cs.getString(5), cs.getString(6), cs.getString(7), cs.getString(8));
                listBaiNghe.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listBaiNghe;
    }

    public ArrayList<nhandangtudbModel> getListNhanDangTu() {
        ArrayList<nhandangtudbModel> listNhanDangTu = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("select * from nhandangtudb order by id asc", null);
            nhandangtudbModel ct;
            while (cs.moveToNext()) {
                ct = new nhandangtudbModel(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3), cs.getString(4), cs.getString(5), cs.getString(6), cs.getString(7));
                listNhanDangTu.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return listNhanDangTu;
    }

    public ArrayList<hinhanhdbModel> getListHinhAnh(){
        ArrayList<hinhanhdbModel> listHinhAnh = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select * from hinhanhdb order by id asc", null);
            hinhanhdbModel ct;
            while (cs.moveToNext()){
                ct = new hinhanhdbModel(cs.getInt(0), cs.getString(1), cs.getString(2),cs.getString(3));
                listHinhAnh.add(ct);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listHinhAnh;
    }
    public ArrayList<maucaudbModel> getListMauCau(){
        ArrayList<maucaudbModel> listMauCau = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select * from maucaudb order by id asc", null);
            maucaudbModel ct;
            while (cs.moveToNext()){
                ct = new maucaudbModel(cs.getInt(0), cs.getString(1), cs.getString(2));
                listMauCau.add(ct);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listMauCau;
    }

    public ArrayList<chudedbModel> getListChuDe(){
        ArrayList<chudedbModel> listChuDe = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select * from chudedb order by id asc", null);
            chudedbModel ct;
            while (cs.moveToNext()){
                ct = new chudedbModel(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3), cs.getString(4), cs.getString(5), cs.getString(6), cs.getString(7), cs.getString(8), cs.getString(9), cs.getString(10));
                listChuDe.add(ct);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listChuDe;
    }

    public ArrayList<trochoidbModel> getListTroChoi(){
        ArrayList<trochoidbModel> listTroChoi = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select * from trochoidb order by id asc", null);
            trochoidbModel ct;
            while (cs.moveToNext()){
                ct = new trochoidbModel(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3), cs.getString(4), cs.getString(5), cs.getString(6), cs.getString(7), cs.getString(8));
                listTroChoi.add(ct);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listTroChoi;
    }
}
