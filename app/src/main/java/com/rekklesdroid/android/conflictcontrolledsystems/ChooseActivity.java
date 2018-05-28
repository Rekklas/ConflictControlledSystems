package com.rekklesdroid.android.conflictcontrolledsystems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xw.repo.BubbleSeekBar;

public class ChooseActivity extends AppCompatActivity {

    private BubbleSeekBar mBsbRowCount;
    private BubbleSeekBar mBsbColumnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        initViews();
    }

    private void initViews() {
        mBsbRowCount = findViewById(R.id.bsb_row_count);
        mBsbColumnCount = findViewById(R.id.bsb_column_count);

        Button btnAffineTransform = findViewById(R.id.btn_affine_transform);

        btnAffineTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, MatrixActivity.class);
                Log.d("ROWS", String.valueOf(mBsbRowCount.getProgress()));
                intent.putExtra(MatrixActivity.EXTRA_ROW_COUNT, mBsbRowCount.getProgress());
                intent.putExtra(MatrixActivity.EXTRA_COLUMN_COUNT, mBsbColumnCount.getProgress());
                startActivity(intent);
            }
        });
    }
}
