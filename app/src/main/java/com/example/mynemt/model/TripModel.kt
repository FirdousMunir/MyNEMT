package com.example.mynemt.model

data class TripModel(

    var id: String? = "" ,
    var client_id: String? = "" ,
    var location_id: String? = "" ,
    var vehicle_id: String? = "" ,
    var trip_leg_id: String? = "" ,
    var title: String? = "" ,
    var type: String? = "" ,
    var order: String? = "" ,
    var date: String? = "" ,
    var created_at: String? = "" ,
    var updated_at: String? = "" ,
    var status: String? = "" ,
    var laravel_through_key: String? = "" ,
    var instructions: String = "" ,
    var phone: String? = "" ,
    var vehicle: String? = "" ,
    var appointment_at: String? = "" ,
    var isActive: String? = "yes" ,
    var address: AddressModel? = null,
    var trip_leg: TripLegModel? = null

)
