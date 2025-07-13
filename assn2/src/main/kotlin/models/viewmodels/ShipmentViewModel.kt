package models.viewmodels

import models.domainmodels.ShippingUpdateDomainModel

data class ShipmentViewModel(
    val id: String,
    val status: String,
    val currentLocation: String,
    val expectedDelivery: Long?,
    val notes: List<String>,
    val updates: List<ShippingUpdateDomainModel>
)