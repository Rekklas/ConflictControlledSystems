package com.rekklesdroid.android.conflictcontrolledsystems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixActivity extends AppCompatActivity {

    /**
     * Intent extras from ChooseActivity
     */
    public static final String EXTRA_ROW_COUNT = "row_count";
    public static final String EXTRA_COLUMN_COUNT = "column_count";

    /**
     * Value for {@link #getRandomValue()}
     */
    private static final int DEFAULT_MAX_RANDOM_VALUE = 31;

    LinearLayout mLinearLayoutMain;

    private List<EditText> mMatrixValues = new ArrayList<>();

    private int rowCount;
    private int columnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);

        getIntentExtras();

        initViews();
    }

    /**
     * Method for initializing of views and creating layout
     */
    private void initViews() {
        mLinearLayoutMain = findViewById(R.id.layout_root_linear);

        TableLayout tableLayout = new TableLayout(this);
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        initTableLayout(tableLayout, lpView);

        Button btnFillMatrixRandomValues = new Button(this);
        btnFillMatrixRandomValues.setText(getResources().getString(R.string.btn_fill_matrix));
        btnFillMatrixRandomValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillMatrixRandomValues();
            }
        });

        Button btnMakeTransform = new Button(this);
        btnMakeTransform.setText(getResources().getString(R.string.btn_make_transform));
        btnMakeTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mLinearLayoutMain.addView(tableLayout, tableParams);
        mLinearLayoutMain.addView(btnFillMatrixRandomValues,lpView);
        mLinearLayoutMain.addView(btnMakeTransform,lpView);
    }

    private void fillMatrixRandomValues() {
        for (EditText editText: mMatrixValues){
            editText.setText(String.valueOf(getRandomValue()));
        }
    }

    /**
     * Create matrix as TableLayout with editTexts
     * @param tableLayout TableLayout ViewGroup
     * @param lpView layout parameters for views inside root layout
     */
    private void initTableLayout(TableLayout tableLayout, LayoutParams lpView) {
        TableRow.LayoutParams edtParams = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        for (int i = 1; i <= rowCount; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 1; j <= columnCount; j++) {
                EditText editText = new EditText(this);
                editText.setLayoutParams(edtParams);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                tableRow.addView(editText);
                mMatrixValues.add(editText);

            }
            tableLayout.addView(tableRow, lpView);
        }
    }

    /**
     * Method generates random value depends on {@see #DEFAULT_MAX_RANDOM_VALUE}
     * e.g. if DEFAULT_MAX_RANDOM_VALUE = 31, then method returns
     * value from [-15;15]
     *
     * @return randomly generated value
     */
    private int getRandomValue() {
        return new Random().nextInt(DEFAULT_MAX_RANDOM_VALUE) - DEFAULT_MAX_RANDOM_VALUE/2;
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        rowCount = intent.getIntExtra(EXTRA_ROW_COUNT, 1);
        columnCount = intent.getIntExtra(EXTRA_COLUMN_COUNT, 1);
    }
}
