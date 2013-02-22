package kr.co.adflow.nms.web.vo;

public class AssetRecord {
	
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
