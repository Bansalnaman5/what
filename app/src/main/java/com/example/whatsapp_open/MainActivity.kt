package com.example.whatsapp_open

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    lateinit var num:TextView;
    lateinit var what:EditText;
    lateinit var start: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        num=findViewById(R.id.num);
        what=findViewById(R.id.what);
        start=findViewById(R.id.start);
        var number:String="0"
        if(intent.action== Intent.ACTION_PROCESS_TEXT){
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(number!="0"){
        num.text=number
            startwhatsapp(number)
        }
//        else{
//            num.text=number;
//            Toast.makeText(this,"Enter only number",Toast.LENGTH_SHORT).show()
//        }
        start.setOnClickListener {
            var n:String=what.text.toString();
            var daa:String="0";
            if(n.length==10){
                daa="91"+n;
            }
            else if(n[0]=='+'){
                daa=n.substring(1);
            }
            else{
                daa=n;
            }
            startwhatsapp(daa);
        }
    }

    private fun startwhatsapp(number: String) {
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        num.text=number.length.toString()
        var data:String="0"
        if(number[0]=='+'){
            data=number.substring(1);
        }
        else if(number.length==11){
            data="91"+number;
        }
        else{
            data=number;
        }
        intent.data= Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(intent,0)!=null){
            startActivity(intent);
        }
        else{
//            num.text=packageManager.resolveActivity(intent,0);
            Toast.makeText(this,"HeHe Error a gaya!!",Toast.LENGTH_SHORT).show();
        }
    }
}