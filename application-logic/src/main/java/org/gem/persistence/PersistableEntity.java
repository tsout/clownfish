package org.gem.persistence;

import java.util.UUID;
import java.util.logging.Logger;

import org.gem.utils.BusinessKey;

public abstract class PersistableEntity {
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private UUID surrogateKey;
	private BusinessKey businessKey;

	public PersistableEntity() {
		initializeSurrogateKey();
	}

	public PersistableEntity(BusinessKey businessKey) {
		if (businessKey == null || !businessKey.isValid()) {
			logger.info("creating a default surrogate key");
			initializeSurrogateKey();
			return;
		}
		logger.info("initializing business key");
		this.businessKey = businessKey;
		this.surrogateKey = null;
	}

	private void initializeSurrogateKey() {
		this.surrogateKey = UUID.randomUUID();
		this.businessKey = null;
		return;
	}

	public String getBusinessKey() {
		if (businessKey != null)
			return businessKey.getBusinessKeyAsString();
		return surrogateKey.toString();
	}

	public void setBusinessKey(BusinessKey businessKey) {
		if (businessKey.isValid()) {
			if (this.businessKey != null) {
				logger.info("a business key can not be overriden onece set");
				// a business key can not be overriden once set
				return;
			}
			if ((businessKey == null || !businessKey.isValid()) && this.surrogateKey!=null) {
				logger.info("creating a default surrogate key");
			 //should never be reached.	
				initializeSurrogateKey();
				return;
			}
			logger.info("creating a new business key");
			this.businessKey = businessKey;
			this.surrogateKey = null;
		}
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getBusinessKey() == null) ? 0 : getBusinessKey().hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersistableEntity other = (PersistableEntity) obj;
		if (getBusinessKey() == null) {
			if (other.getBusinessKey() != null)
				return false;
		} else if (!getBusinessKey().equals(other.getBusinessKey()))
			return false;
		return true;
	}
}
