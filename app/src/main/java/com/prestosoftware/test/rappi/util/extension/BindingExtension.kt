package com.prestosoftware.test.rappi.util.extension

import android.view.View
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.Status
import org.jetbrains.anko.toast

fun View.bindResource(resource: Resource<Any>?, onSuccess: () -> Unit) {
    if (resource != null) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> onSuccess()
            Status.ERROR -> this.context.toast(resource.errorEnvelope?.statusMessage.toString())
        }
    }
}
