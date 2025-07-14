package models.viewmodels

import models.domainmodels.ShippingUpdateDomainModel
import java.time.Instant

data class ShipmentViewModel(
    val id: String,
    val status: String,
    val currentLocation: String,
    val expectedDelivery: Instant?,
    val notes: List<String>,
    val updates: List<ShippingUpdateDomainModel>
)