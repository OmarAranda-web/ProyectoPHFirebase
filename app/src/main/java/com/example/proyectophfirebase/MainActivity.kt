package com.example.proyectophfirebase

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var btnCancelar:Button
    private lateinit var btnObtener:Button
    private lateinit var lblMostar:TextView
    private lateinit var lblDesc:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCancelar=findViewById(R.id.buttonCancelar)
        btnObtener=findViewById(R.id.buttonVer)
        lblDesc=findViewById(R.id.txtNivel)
        lblMostar=findViewById(R.id.lblMostrar)

        obtener()
        cancelar()
    }

    private fun cancelar() {
        btnCancelar.setOnClickListener{
            finish()
        }
    }

    private fun obtener() {
        btnObtener.setOnClickListener{
            try {
                var db = FirebaseDatabase.getInstance()
                var dbref=db.getReference("ph")

                dbref.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            var ph:Int = snapshot.getValue().toString().toInt()
                            if(ph==0){
                                lblDesc.setText("Este nivel de PH equivale al liquido de las baterias")
                                lblMostar.setTextColor(Color.parseColor("#D50000"))
                            }
                            if(ph==1){
                                lblDesc.setText("Este nivel de PH equivale acido estomacal")
                                lblMostar.setTextColor(Color.parseColor("#FF6F00"))
                            }
                            if(ph==2){
                                lblDesc.setText("Este nivel de PH equivale a jugo de limon")
                                lblMostar.setTextColor(Color.parseColor("#FFC400"))
                            }
                            if(ph==3){
                                lblDesc.setText("Este nivel de ph equivale al vinagre")
                                lblMostar.setTextColor(Color.parseColor("#FFFF00    "))
                            }
                            if(ph==4){
                                lblDesc.setText("Este nivel equivale al jugo de tomate")
                                lblMostar.setTextColor(Color.parseColor("#C6FF00"))
                            }
                            if(ph==5){
                                lblDesc.setText("Este nivel de ph Equivale al cafe")
                                lblMostar.setTextColor(Color.parseColor("#AEEA00"))
                            }
                            if(ph==6){
                                lblDesc.setText("Este nivel equivale al de la leche")
                                lblMostar.setTextColor(Color.parseColor("#64DD17"))

                            }
                            if(ph==7){
                                lblDesc.setText("Este nivel equivale al del agua")
                                lblMostar.setTextColor(Color.parseColor("#00C853"))
                            }
                            if(ph==8){
                                lblDesc.setText("Este nivel equivale al de la sangre")
                                lblMostar.setTextColor(Color.parseColor("#00E676"))
                            }
                            if(ph==9){
                                lblDesc.setText("Este nivel equivale al del bicarbonato de soda")
                                lblMostar.setTextColor(Color.parseColor("#69F0AE"))
                            }
                            if(ph==10){
                                lblDesc.setText("Este nivel equivale a tabletas estomacales")
                                lblMostar.setTextColor(Color.parseColor("#039BE5"))
                            }
                            if(ph==11){
                                lblDesc.setText("Este nivel equivale a una solucion de amoniaco")
                                lblMostar.setTextColor(Color.parseColor("#0277BD"))
                            }
                            if(ph==12){
                                lblDesc.setText("Este nivel equivale a agua jabonosa")
                                lblMostar.setTextColor(Color.parseColor("#01579B"))
                            }
                            if(ph==13){
                                lblDesc.setText("Este nivel equivale a blanqueador")
                                lblMostar.setTextColor(Color.parseColor("#7E57C2"))
                            }
                            if(ph==14){
                                lblDesc.setText("Este nivel equivale al limpiador de cañerias")
                                lblMostar.setTextColor(Color.parseColor("#1A237E"))
                            }
                            lblMostar.setText(ph.toString())
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@MainActivity,"Error al conectar",Toast.LENGTH_SHORT).show()
                    }

                })
            }catch (e:Exception){
                Toast.makeText(this,"Error $e",Toast.LENGTH_SHORT).show()
            }
        }
    }
}