package mx.edu.ittepic.tpdm_u2_practica2_delarosaguerrerogabriela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main7principal extends AppCompatActivity {
    Button prop, seguro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        prop = findViewById(R.id.propietario);
        seguro = findViewById(R.id.seguro);

        prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main7principal.this,Mainppropietario.class);
                startActivity(i);
                finish();
            }
        });

        seguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main7principal.this,Main6pseguro.class);
                startActivity(i);
                finish();
            }
        });
    }
}
