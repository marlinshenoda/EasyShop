package com.payex.shopease;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    private TextView transactionDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        transactionDetailsTextView = findViewById(R.id.transaction_details_textview);

        // Get the items and total price from the intent
        ArrayList<String> transactionItems = getIntent().getStringArrayListExtra("transactionItems");
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);

        // Display the transaction details
        StringBuilder details = new StringBuilder("Transaction Details:\n\nItems:\n");
        assert transactionItems != null;
        for (String item : transactionItems) {
            details.append("- ").append(item).append("\n");
        }
        details.append("\nTotal Price: $").append(totalPrice);

        transactionDetailsTextView.setText(details.toString());
    }
}
