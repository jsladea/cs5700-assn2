package updatestrategies

import models.datamodels.ShippingUpdateDataModel
import models.domainmodels.ShipmentDomainModel

class LocationStrategy : IShipmentUpdateStrategy {
    override fun apply(shipment: ShipmentDomainModel, update: ShippingUpdateDataModel) {
        shipment.currentLocation = update.otherInfo ?: shipment.currentLocation
    }
}