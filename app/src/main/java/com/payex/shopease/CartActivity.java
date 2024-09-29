package com.payex.shopease;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private TextView totalPriceTextView;
    private Button proceedToPaymentButton;
    private ArrayList<String> cartItems;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize UI components
        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        totalPriceTextView = findViewById(R.id.total_price_textview);
        proceedToPaymentButton = findViewById(R.id.proceed_to_payment_button);

        // Get the items from the intent
        cartItems = getIntent().getStringArrayListExtra("cartItems");

        // Display the items in a RecyclerView (implementation can vary based on your adapter)
        // Assuming a simple adapter to show items:
        CartAdapter adapter = new CartAdapter(cartItems);
        cartRecyclerView.setAdapter(adapter);

        // Calculate total price (dummy calculation for demo purposes)
        int totalPrice = cartItems.size() * 10; // Assuming each item costs 10
        totalPriceTextView.setText("Total Price: $" + totalPrice);

        // Handle the "Proceed to Payment" button click
        proceedToPaymentButton.setOnClickListener(view -> {
            // Navigate to TransactionActivity
            Intent intent = new Intent(CartActivity.this, TransactionActivity.class);
            intent.putStringArrayListExtra("transactionItems", cartItems);
            intent.putExtra("totalPrice", totalPrice);
            startActivity(intent);
        });
    }

}
