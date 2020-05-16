package com.zeronesgame;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.zeronesgame.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG";
    private ActivityMainBinding mBinding;
    private ButtonAdapter mButtonAdapter;
    private int[] mStringList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private String mStrEnteredNo, mStrGeneratedNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initWidgets();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initWidgets() {
//        mBinding.mEdtPlayer1.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//
//                }
//                return false;
//            }
//        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mButtonAdapter = new ButtonAdapter(this, mStringList) {
            @Override
            public void onItemClick(int pos) {
                super.onItemClick(pos);
                mStrEnteredNo = mBinding.mEdtPlayer1.getText().toString();
                generateRandomNo(mStringList);
                if (TextUtils.isEmpty(mStrEnteredNo) || TextUtils.isEmpty(mStrGeneratedNo) ||
                        TextUtils.isEmpty(mBinding.mEdtPlayer1.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter number", Toast.LENGTH_SHORT).show();
                    mStrEnteredNo = "";
                    mStrGeneratedNo = "";
                }else {
                    if ((mStringList[pos] + "").equalsIgnoreCase(mStrEnteredNo)
                            || (mStringList[pos] + "").equalsIgnoreCase(mStrGeneratedNo)) {
                        Toast.makeText(MainActivity.this, "YOU ARE OUT", Toast.LENGTH_SHORT).show();
                    } else {
                        mStringList[pos] = 0;
                    }
                    Log.d(TAG, " Remaining LIST " + Arrays.toString(mStringList));
                    mStrEnteredNo = "";
                    mStrGeneratedNo = "";
                    mBinding.mEdtPlayer1.setText("");
                    notifyDataSetChanged();
                }
            }
        };
        mBinding.mRvNumbers.setLayoutManager(gridLayoutManager);
        mBinding.mRvNumbers.setAdapter(mButtonAdapter);
    }

    private void generateRandomNo(int[] arr) {
        Random r = new Random();
        int randomIndex = r.nextInt(arr.length);
        int randomVal = arr[randomIndex];
        if (randomVal == 0) {
            generateRandomNo(arr);
        } else {
            mStrGeneratedNo = randomVal + "";
        }
    }
}
