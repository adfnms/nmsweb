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



}
