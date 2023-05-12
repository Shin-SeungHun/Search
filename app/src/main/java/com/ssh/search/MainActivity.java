package com.ssh.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 뒤로가기 버튼을 툴바에 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed(); // 뒤로가기 버튼을 클릭하면 액티비티 종료
        });

        // 커스텀 서치뷰 레이아웃을 툴바에 설정
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View searchViewLayout = inflater.inflate(R.layout.custom_search_view, toolbar, false);
        toolbar.addView(searchViewLayout);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setQueryHint("프로그램, 영화, 배우 검색...");  // 원하는 힌트로 변경
        searchView.setIconified(false); // SearchView 확장

        resultTextView = findViewById(R.id.result_text_view); // 결과를 표시할 TextView

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT);

        searchView.setOnClickListener(v -> {
            searchView.setIconified(false); // SearchView 확장
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색어 제출 시 처리할 내용을 작성합니다.
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경될 때마다 처리할 내용을 작성합니다.
                resultTextView.setText(newText); // 텍스트뷰에 검색어 표시
                return true;
            }
        });
    }
}