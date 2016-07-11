package com.example.windowslive.sqlite;


        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DbHelper helper = new DbHelper(this,"carDb",null,1);

        findViewById(R.id.btnInsert).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SQLiteDatabase sqDb =
                                helper.getWritableDatabase();

                        ContentValues values =
                                new ContentValues();
                        values.put("carName","Audi");
                        values.put("carNum",4562);
                        sqDb.insert("carTab",null,values);

                        sqDb.close();
                    }
                });

        findViewById(R.id.btnDisplay).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SQLiteDatabase sqDb =
                                helper.getWritableDatabase();

                        String table = "carTab";
                        //String[] columns = {"carName"};
                        String[] columns = null;
                        //String selection = "carNum = ?";
                        String selection = null;
                        //String[] selectionArgs = {"4562"};
                        String[] selectionArgs = null;
                        String groupBy = null;
                        String having = null;
                        String orderBy = null;

                        Cursor cursor = sqDb.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);

                        while(cursor.moveToNext()){

                            String carName = cursor.getString(cursor.getColumnIndex("carName"));
                            Integer carNum = cursor.getInt(cursor.getColumnIndex("carNum"));
                            Log.i("@codekul","Car Name - "+carName +" Car Num - "+carNum);
                        }

                        sqDb.close();
                    }
                });

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqDb =
                        helper.getWritableDatabase();

                String table = "carTab";
                ContentValues values = new ContentValues();
                values.put("carNum",111);
                String whereClause = "carName = ?";
                String[] whereArgs = {"Audi"};

                sqDb.update(table,values,whereClause,whereArgs);

                sqDb.close();
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqDb = helper.getWritableDatabase();

                String table = "carTab";
                String whereClause = "carNum = ?";
                String[] whereArgs = {"111"};

                sqDb.delete(table,whereClause,whereArgs);

                sqDb.close();
            }
        });
    }
}

