package org.playground.jmoneyserver.web.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
//TODO move to swagger
public class RequestPayment {

	private long userId;
	private List<AddPayment> addPayments;
	private List<RemovePayment> removePayments;

}
