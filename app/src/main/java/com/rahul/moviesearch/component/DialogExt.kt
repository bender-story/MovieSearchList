package com.rahul.moviesearch.component

import android.app.AlertDialog
import android.content.Context

class DialogExt( val context : Context){
    // Build Error dialog.
    fun buildDialog(error:String , onReload:() -> Unit){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(error)
        builder.setPositiveButton("Reload"){dialog, which ->
            onReload.invoke()
            dialog.dismiss()
        }

        builder.setNegativeButton("No"){dialog,which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}