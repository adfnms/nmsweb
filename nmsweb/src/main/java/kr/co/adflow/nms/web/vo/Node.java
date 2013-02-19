package kr.co.adflow.nms.web.vo;

public class Node {

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLabelSource() {
		return labelSource;
	}

	public void setLabelSource(String labelSource) {
		this.labelSource = labelSource;
	}

	public String getLastCapsdPoll() {
		return lastCapsdPoll;
	}

	public void setLastCapsdPoll(String lastCapsdPoll) {
		this.lastCapsdPoll = lastCapsdPoll;
	}

	private String type;
	private String id;
	private String label;
	private String createTime;
	private String labelSource;
	private String lastCapsdPoll;
	

	private AssetRecord assetRecord;
	

	public AssetRecord getAssetRecord() {
		return assetRecord;
	}

	public void setAssetRecord(AssetRecord assetRecord) {
		this.assetRecord = assetRecord;
	}


	public static class AssetRecord {

		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getDisplayCategory() {
			return displayCategory;
		}
		public void setDisplayCategory(String displayCategory) {
			this.displayCategory = displayCategory;
		}
		public String getLastModifiedBy() {
			return lastModifiedBy;
		}
		public void setLastModifiedBy(String lastModifiedBy) {
			this.lastModifiedBy = lastModifiedBy;
		}
		public String getLastModifiedDate() {
			return lastModifiedDate;
		}
		public void setLastModifiedDate(String lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}
		public String getNode() {
			return node;
		}
		public void setNode(String node) {
			this.node = node;
		}
		public String getNotifyCategory() {
			return notifyCategory;
		}
		public void setNotifyCategory(String notifyCategory) {
			this.notifyCategory = notifyCategory;
		}
		public String getPollerCategory() {
			return pollerCategory;
		}
		public void setPollerCategory(String pollerCategory) {
			this.pollerCategory = pollerCategory;
		}
		public String getThresholdCategory() {
			return thresholdCategory;
		}
		public void setThresholdCategory(String thresholdCategory) {
			this.thresholdCategory = thresholdCategory;
		}
		private String category;
		private String displayCategory;
		private String lastModifiedBy;
		private String lastModifiedDate;
		private String node;
		private String notifyCategory;
		private String pollerCategory;
		private String thresholdCategory;
	}
}
