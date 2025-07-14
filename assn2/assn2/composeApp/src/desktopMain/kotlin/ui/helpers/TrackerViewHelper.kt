package ui.helpers

import models.domainmodels.ShipmentDomainModel
import models.interfaces.IObserver
import androidx.compose.runtime.mutableStateOf

class TrackerViewHelper : IObserver<ShipmentDomainModel> {
    private val _shipmentId = mutableStateOf("")
    private val _shipmentStatus = mutableStateOf("")
    private val _shipmentNotes = mutableStateListOf<String>()
    private val _shipmentUpdateHistory = mutableStateListOf<String>()
    private val _expectedShipmentDeliveryDate = mutableStateOf("")
    private val _shipmentLocation = mutableStateOf("")

    val shipmentId: State<String> get() = _shipmentId
    val shipmentStatus: State<String> get() = _shipmentStatus
    val shipmentNotes: List<String> get() = _shipmentNotes
    val shipmentUpdateHistory: List<String> get() = _shipmentUpdateHistory
    val expectedShipmentDeliveryDate: State<String> get() = _expectedShipmentDeliveryDate
    val shipmentLocation: State<String> get() = _shipmentLocation

    private var trackedShipment: Shipment? = null

    fun trackShipment(shipment: Shipment) {
        trackedShipment?.removeObserver(this)
        trackedShipment = shipment
        _shipmentId.value = shipment.id
        shipment.addObserver(this)
        updateFromSnapshot(shipment.toViewModel())
    }

    fun stopTracking() {
        trackedShipment?.removeObserver(this)
        trackedShipment = null
        clearState()
    }

    override fun update(shipment: Shipment) {
        updateFromSnapshot(shipment.toViewModel())
    }

    private fun updateFromSnapshot(snapshot: ShipmentViewModel) {
        _shipmentStatus.value = snapshot.status
        _shipmentNotes.clear()
        _shipmentNotes.addAll(snapshot.notes)
        _shipmentUpdateHistory.clear()
        _shipmentUpdateHistory.addAll(snapshot.updates.map {
            "Shipment went from ${it.previousStatus} to ${it.newStatus} on ${it.timestamp}"
        })
        _shipmentLocation.value = snapshot.currentLocation
        _expectedShipmentDeliveryDate.value = snapshot.expectedDelivery?.atZone(ZoneId.systemDefault())
            ?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) ?: ""
    }

    private fun clearState() {
        _shipmentId.value = ""
        _shipmentStatus.value = ""
        _shipmentNotes.clear()
        _shipmentUpdateHistory.clear()
        _shipmentLocation.value = ""
        _expectedShipmentDeliveryDate.value = ""
    }

    private fun Shipment.toViewModel(): ShipmentViewModel = ShipmentViewModel(
        id = this.id,
        status = this.status,
        currentLocation = this.currentLocation,
        expectedDelivery = this.expectedDelivery,
        notes = this.getNotes(),
        updates = this.getUpdates()
    )
}