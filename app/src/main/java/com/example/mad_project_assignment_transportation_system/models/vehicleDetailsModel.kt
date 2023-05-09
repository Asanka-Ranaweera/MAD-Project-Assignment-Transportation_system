package com.example.mad_project_assignment_transportation_system.models

import android.widget.Spinner

data class vehicleDetailsModel(

    var vehicleId: String? = null,
    var vehicleRegNumber: String? = null,
    var VehicleModel: String? = null,
    var PhoneNumb: String? = null,
    var NumOfSeats: String? = null,
    var StartingPoint: String? = null,
    var Destination: String? = null,
    var StartTimeMorning: String? = null,
    var ArrivalTimeMorning: String? = null,
    var StartTimeEvening: String? = null,
    var ArrivalTimeEvening: String? = null,
    var Route: String? = null,
    var spinnerType: String? = null,
    var userEmail: String? = null,
    val bookingCount: Int? = null






)
