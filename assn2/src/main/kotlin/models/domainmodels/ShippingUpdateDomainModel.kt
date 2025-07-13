package models.domainmodels

data class ShippingUpdateDomainModel(
    val previousStatus: String,
    val newStatus: String,
    val timestamp: Long
)