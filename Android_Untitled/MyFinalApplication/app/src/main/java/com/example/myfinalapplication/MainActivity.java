package com.example.myfinalapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {



    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview1, imageview2, imageview3;
    private int inum;
    //Millisecond 형태의 하루(24 시간)
    private final int ONE_DAY = 24 * 60 * 60 * 1000;

    //현재 날짜를 알기 위해 사용
    private Calendar mCalender;

    //D-day result
    private TextView mTvResult;

    //DatePicker 에서 날짜 선택 시 호출
    // DatePicker 에서 날짜 선택 시 호출
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker a_view, int a_year, int a_monthOfYear, int a_dayOfMonth) {
            // D-day 계산 결과 출력
            mTvResult.setText(getDday(a_year, a_monthOfYear, a_dayOfMonth));
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);

        // 한국어 설정 (ex: date picker)
        Locale.setDefault(Locale.KOREAN);

        // 현재 날짜를 알기 위해 사용
        mCalender = new GregorianCalendar();

        // Today 보여주기
        //TextView tvDate = findViewById(R.id.);
        //tvDate.setText(getToday());


        // D-day 보여주기
        mTvResult = findViewById(R.id.DDay);

        imageview1 = (ImageView) findViewById(R.id.imageView1);
        imageview1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inum =1;

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);


            }
        });

        imageview2 = (ImageView) findViewById(R.id.imageView2);
        imageview2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inum=2;

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        imageview3 = (ImageView) findViewById(R.id.imageView3);
        imageview3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inum=3;

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });




        // Input date click 시 date picker 호출

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int year = mCalender.get(Calendar.YEAR);
                final int month = mCalender.get(Calendar.MONTH);
                final int day = mCalender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, mDateSetListener, year, month, day);
                dialog.show();
            }
        };
        findViewById(R.id.button).setOnClickListener(clickListener);

    }




    /**
     * Today 반환
     */


    /**
     * D-day 반한
     */
    private String getDday(int a_year, int a_monthOfYear, int a_dayOfMonth) {
        //D-day 설정
        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(a_year, a_monthOfYear, a_dayOfMonth);

        //D-day 를 구하기 위해 millisecond 으로 환산하여 d-day 에서 today 의 차를 구한다.
        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;

        //출력 시 D-day 에 맞게 표시
        final String strFormat;
        if (result > 0) {
            strFormat = "D-%d";
        } else if (result == 0) {
            strFormat = "D-Day";
        } else {
            result *= -1;
            strFormat = "D+%d";
        }

        final String strCount = (String.format(strFormat, result));
        return strCount;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            if(inum == 1){
            imageview1.setImageURI(selectedImageUri);}
            else if(inum==2){
                imageview2.setImageURI(selectedImageUri);
            }else{imageview3.setImageURI(selectedImageUri);}
        }
    }




        }
