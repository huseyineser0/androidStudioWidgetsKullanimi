package com.example.widgets;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.widgets.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean kontrol = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonOku.setOnClickListener(view -> {
            String alinanVeri = binding.editTextGirdi.getText().toString();//burada stringe biz çevirdik editTexi toString yazarak
            binding.textViewSonuc.setText(alinanVeri);
        });

        binding.buttonResim1.setOnClickListener(view -> {
            binding.imageView.setImageResource(R.drawable.mutlu_resim);
            //parantez içi R->res'i ifade ediyor
            //drawable
            //sonrası da hangi resmi seçmek istiyorsan onu seçiiyorsun
            //bu yol genelde kullanılmıyor aşagıdaki yöntem daha çok kullanılır
            // veri tabanından genelde resimleri çektiğimiz için
        });

        binding.buttonResim2.setOnClickListener(view -> {
            binding.imageView.setImageResource(getResources()
                    .getIdentifier("uzgun_resim", "drawable", getPackageName()));
            // burada önce resim dosyasının ismini yazdık
            // daha sonra hangi package de ise onun ismini yazdık
        });

        binding.switch1.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                Log.e("Sonuç", "Switch: On");
            } else {
                Log.e("Sonuç", "Switch: Off");
            }
        });
        binding.buttonGoster.setOnClickListener(view -> {
            Log.e("Sonuç", "Switch Durum:" + binding.switch1.isChecked());
            //+binding.switch1.isChecked()) bununla switch durumunu öğreniyooruz
        });

        binding.buttonBasla.setOnClickListener(view -> {
            binding.progressBar.setVisibility(view.VISIBLE);

        });
        binding.buttonDur.setOnClickListener(view->{
            binding.progressBar.setVisibility(View.INVISIBLE);
        });

        binding.toggleButton.addOnButtonCheckedListener(((group, checkedId, isChecked) -> {
            kontrol = isChecked;
            // yukarıda kontrol diye boolean tanımladık çğnkü toggle de
            // hangi buton seçilimi seğilmi kontrol etmeliyiz yoksa uygulama çöker
            if (kontrol) {
                Button secilenButton = findViewById(binding.toggleButton.getCheckedButtonId());
                String buttonYazi = secilenButton.getText().toString();
                Log.e("Sonuç", buttonYazi);
            }
        }));

        ArrayList ulkler = new ArrayList<>();
        ulkler.add("Türkiye");
        ulkler.add("İtalya");
        ulkler.add("Japonya");
        ulkler.add("Kırgısiztan");
        ulkler.add("Rusya");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ulkler);
        binding.autoCompleteTextView.setAdapter(arrayAdapter);
        // bu autoCompleteTextView arama motoruna yazdığımız harf ile eşleşen ülkeleri getirmek için kullanılır

        binding.buttonGoster.setOnClickListener(view -> {

            Log.e("Sonuç", "Switch Durum:" + binding.switch1.isChecked());
            if (kontrol) {
                Button secilenButton = findViewById(binding.toggleButton.getCheckedButtonId());
                String buttonYazi = secilenButton.getText().toString();
                Log.e("Sonuç", "Toggle Durum:" + buttonYazi);
            }
            String ulke = binding.autoCompleteTextView.getText().toString();
            Log.e("Sonuç", "Ülke Durum:" + ulke);
        });

    }

}