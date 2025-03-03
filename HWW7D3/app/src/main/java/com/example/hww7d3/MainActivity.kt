package com.example.hww7d3

import android.Manifest
import android.content.pm.PackageManager
import android.icu.util.TimeUnit
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkedPermision()
    }
    fun checkedPermision(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION),2)

        }else{
            var worker = PeriodicWorkRequestBuilder<LocationWorker>(10,
                java.util.concurrent.TimeUnit.SECONDS
            ).build()
            WorkManager.getInstance(this).enqueue(worker)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            checkedPermision()
        }else{
            AlertDialog.Builder(this).apply {
                title="warning"
                setMessage("access loction:enter the settings")
                setPositiveButton("Ok",{dialog, which->



                })
            }.show()
        }
    }




}





