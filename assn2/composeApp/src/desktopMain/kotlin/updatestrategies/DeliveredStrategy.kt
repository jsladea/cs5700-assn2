package updatestrategies

import constants.ShipmentStatus
import models.datamodels.ShippingUpdateDataModel
import models.domainmodels.ShipmentDomainModel
import models.domainmodels.ShippingUpdateDomainModel

class DeliveredStrategy : IShipmentUpdateStrategy {
    override fun apply(shipment: ShipmentDomainModel, update: ShippingUpdateDataModel) {
        val shippingUpdate = ShippingUpdateDomainModel(shipment.status, ShipmentStatus.DELIVERED, update.updateTimestamp)
        shipment.addUpdate(shippingUpdate)
        shipment.status = ShipmentStatus.DELIVERED
    }
}