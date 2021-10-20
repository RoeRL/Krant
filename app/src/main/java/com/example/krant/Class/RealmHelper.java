package com.example.krant.Class;

import android.util.Log;

import com.example.krant.Model.DataNoteModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    private Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //Function untuk menyimpan data
    public void Save(DataNoteModel dataNoteModel){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){

                    Number current_id = realm.where(DataNoteModel.class).max("id");
                    int nextId;
                    if (current_id == null){
                        nextId = 1;
                    }
                    else {
                        nextId = current_id.intValue() + 1;
                    }

                    dataNoteModel.setId(nextId);
                    realm.copyToRealm(dataNoteModel);

                    Log.d("Create", "execute: Database telah dibuat");
                }
                else {
                    Log.d("Create", "execute: Database gagal dibuat");
                }
            }
        });
    }

    //Function untuk memanggil data
    public List<DataNoteModel> getAllData(){
        RealmResults<DataNoteModel> results = realm.where(DataNoteModel.class).findAll();
        return results;
    }

    // untuk meng-update data
    public void Update(int id, String title, String content){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DataNoteModel dataNoteModel = realm.where(DataNoteModel.class)
                        .equalTo("id", id)
                        .findFirst();
                dataNoteModel.setTitle(title);
                dataNoteModel.setContent(content);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    //Function untuk menghapus data
    public void Delete(int id){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<DataNoteModel> results = realm.where(DataNoteModel.class).equalTo("id", id).findAll();
                Log.i("pppppppppppppppp", "getAllData: "+results.size());
                results.deleteFromRealm(0);
            }
        });
    }
}
