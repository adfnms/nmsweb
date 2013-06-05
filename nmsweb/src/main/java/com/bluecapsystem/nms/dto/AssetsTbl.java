package com.bluecapsystem.nms.dto;

import java.util.Date;



public class AssetsTbl {

	private Integer id;
	private Integer nodeid;	
	private String 	nodeLabel;
	private String 	category;
	private String 	manufacturer;
	private String	vendor;
	private String	modelnumber;
	private String	serialnumber;
	private String	description;
	private String	circuitid;
	private String	assetnumber;
	private String	operatingsystem;
	private String	rack;
	private String	slot;
	private String	port;
	private String	region;
	private String	division;
	private String	department;
	private String	address1;
	private String	address2;
	private String	city;
	private String	state;
	private String	zip;
	private String	building;
	private String	floor;
	private String	room;
	private String	vendorphone;
	private String	vendorfax;
	private String	vendorassetnumber;
	private String	userlastmodified;
	private Date	lastmodifieddate;
	private String	dateinstalled;
	private String	lease;
	private String	leaseexpires;
	private String	supportphone;
	private String	maintcontract;
	private String	maintcontractexpires;
	private String	displaycategory;
	private String	notifycategory;
	private String	pollercategory;
	private String	thresholdcategory;
	private String	comment;
	private String	managedobjectinstance;
	private String	managedobjecttype;
	private String	username ;
	private String	password;
	private String	enable;
	private String	autoenable;
	private String	connection;
	private String	cpu;
	private String	ram;
	private String	storagectrl;
	private String	hdd1;
	private String	hdd2;
	private String	hdd3;
	private String	hdd4;
	private String	hdd5;
	private String	hdd6;
	private String	numpowersupplies;
	private String	inputpower;
	private String	additionalhardware;
	private String	admin;
	private String	snmpcommunity;
	private String	rackunitheight;
	
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("AssetsTbl[");
		
		sb.append(String.format("id:%s,", getId()));
		sb.append(String.format("nodeid:%s,", getNodeid()));
		sb.append(String.format("nodeLabel:%s,", getNodeLabel()));
		sb.append(String.format("category:%s,", getCategory()));
		sb.append(String.format("manufacturer:%s,", getManufacturer()));
		sb.append(String.format("vendor:%s,", getVendor()));
		sb.append(String.format("modelnumber:%s,", getModelnumber()));
		sb.append(String.format("serialnumber:%s,", getSerialnumber()));
		sb.append(String.format("description:%s,", getDescription()));
		sb.append(String.format("circuitid:%s,", getCircuitid()));
		sb.append(String.format("assetnumber:%s,", getAssetnumber()));
		sb.append(String.format("operatingsystem:%s,", getOperatingsystem()));
		sb.append(String.format("rack:%s,", getRack()));
		sb.append(String.format("slot:%s,", getSlot()));
		sb.append(String.format("port:%s,", getPort()));
		sb.append(String.format("region:%s,", getRegion()));
		sb.append(String.format("division:%s,", getDivision()));
		sb.append(String.format("department:%s,", getDepartment()));
		sb.append(String.format("address1:%s,", getAddress1()));
		sb.append(String.format("address2:%s,", getAddress2()));
		sb.append(String.format("city:%s,", getCity()));
		sb.append(String.format("state:%s,", getState()));
		sb.append(String.format("zip:%s,", getZip()));
		sb.append(String.format("building:%s,", getBuilding()));
		sb.append(String.format("floor:%s,", getFloor()));
		sb.append(String.format("room:%s,", getRoom()));
		sb.append(String.format("vendorphone:%s,", getVendorphone()));
		sb.append(String.format("vendorfax:%s,", getVendorfax()));
		sb.append(String.format("vendorassetnumber:%s,", getVendorassetnumber()));
		sb.append(String.format("userlastmodified:%s,", getUserlastmodified()));
		sb.append(String.format("lastmodifieddate:%s,", getLastmodifieddate()));
		sb.append(String.format("dateinstalled:%s,", getDateinstalled()));
		sb.append(String.format("lease:%s,", getLease()));
		sb.append(String.format("leaseexpires:%s,", getLeaseexpires()));
		sb.append(String.format("supportphone:%s,", getSupportphone()));
		sb.append(String.format("maintcontract:%s,", getMaintcontract()));
		sb.append(String.format("maintcontractexpires:%s,", getMaintcontractexpires()));
		sb.append(String.format("displaycategory:%s,", getDisplaycategory()));
		sb.append(String.format("notifycategory:%s,", getNotifycategory()));
		sb.append(String.format("pollercategory:%s,", getPollercategory()));
		sb.append(String.format("thresholdcategory:%s,", getThresholdcategory()));
		sb.append(String.format("comment:%s,", getComment()));
		sb.append(String.format("managedobjectinstance:%s,", getManagedobjectinstance()));
		sb.append(String.format("managedobjecttype:%s,", getManagedobjecttype()));
		sb.append(String.format("username:%s,", getUsername()));
		sb.append(String.format("password:%s,", getPassword()));
		sb.append(String.format("enable:%s,", getEnable()));
		sb.append(String.format("autoenable:%s,", getAutoenable()));
		sb.append(String.format("connection:%s,", getConnection()));
		sb.append(String.format("cpu:%s,", getCpu()));
		sb.append(String.format("ram:%s,", getRam()));
		sb.append(String.format("storagectrl:%s,", getStoragectrl()));
		sb.append(String.format("hdd1:%s,", getHdd1()));
		sb.append(String.format("hdd2:%s,", getHdd2()));
		sb.append(String.format("hdd3:%s,", getHdd3()));
		sb.append(String.format("hdd4:%s,", getHdd4()));
		sb.append(String.format("hdd5:%s,", getHdd5()));
		sb.append(String.format("hdd6:%s,", getHdd6()));
		sb.append(String.format("numpowersupplies:%s,", getNumpowersupplies()));
		sb.append(String.format("inputpower:%s,", getInputpower()));
		sb.append(String.format("additionalhardware:%s,", getAdditionalhardware()));
		sb.append(String.format("admin:%s,", getAdmin()));
		sb.append(String.format("snmpcommunity:%s,", getSnmpcommunity()));
		sb.append(String.format("rackunitheight:%s,", getRackunitheight()));
		sb.append("]");
		
		return sb.toString();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNodeid() {
		return nodeid;
	}


	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}


	public String getNodeLabel() {
		return nodeLabel;
	}


	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getVendor() {
		return vendor;
	}


	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public String getModelnumber() {
		return modelnumber;
	}


	public void setModelnumber(String modelnumber) {
		this.modelnumber = modelnumber;
	}


	public String getSerialnumber() {
		return serialnumber;
	}


	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCircuitid() {
		return circuitid;
	}


	public void setCircuitid(String circuitid) {
		this.circuitid = circuitid;
	}


	public String getAssetnumber() {
		return assetnumber;
	}


	public void setAssetnumber(String assetnumber) {
		this.assetnumber = assetnumber;
	}


	public String getOperatingsystem() {
		return operatingsystem;
	}


	public void setOperatingsystem(String operatingsystem) {
		this.operatingsystem = operatingsystem;
	}


	public String getRack() {
		return rack;
	}


	public void setRack(String rack) {
		this.rack = rack;
	}


	public String getSlot() {
		return slot;
	}


	public void setSlot(String slot) {
		this.slot = slot;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getBuilding() {
		return building;
	}


	public void setBuilding(String building) {
		this.building = building;
	}


	public String getFloor() {
		return floor;
	}


	public void setFloor(String floor) {
		this.floor = floor;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public String getVendorphone() {
		return vendorphone;
	}


	public void setVendorphone(String vendorphone) {
		this.vendorphone = vendorphone;
	}


	public String getVendorfax() {
		return vendorfax;
	}


	public void setVendorfax(String vendorfax) {
		this.vendorfax = vendorfax;
	}


	public String getVendorassetnumber() {
		return vendorassetnumber;
	}


	public void setVendorassetnumber(String vendorassetnumber) {
		this.vendorassetnumber = vendorassetnumber;
	}


	public String getUserlastmodified() {
		return userlastmodified;
	}


	public void setUserlastmodified(String userlastmodified) {
		this.userlastmodified = userlastmodified;
	}


	public Date getLastmodifieddate() {
		return lastmodifieddate;
	}


	public void setLastmodifieddate(Date lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}


	public String getDateinstalled() {
		return dateinstalled;
	}


	public void setDateinstalled(String dateinstalled) {
		this.dateinstalled = dateinstalled;
	}


	public String getLease() {
		return lease;
	}


	public void setLease(String lease) {
		this.lease = lease;
	}


	public String getLeaseexpires() {
		return leaseexpires;
	}


	public void setLeaseexpires(String leaseexpires) {
		this.leaseexpires = leaseexpires;
	}


	public String getSupportphone() {
		return supportphone;
	}


	public void setSupportphone(String supportphone) {
		this.supportphone = supportphone;
	}


	public String getMaintcontract() {
		return maintcontract;
	}


	public void setMaintcontract(String maintcontract) {
		this.maintcontract = maintcontract;
	}


	public String getMaintcontractexpires() {
		return maintcontractexpires;
	}


	public void setMaintcontractexpires(String maintcontractexpires) {
		this.maintcontractexpires = maintcontractexpires;
	}


	public String getDisplaycategory() {
		return displaycategory;
	}


	public void setDisplaycategory(String displaycategory) {
		this.displaycategory = displaycategory;
	}


	public String getNotifycategory() {
		return notifycategory;
	}


	public void setNotifycategory(String notifycategory) {
		this.notifycategory = notifycategory;
	}


	public String getPollercategory() {
		return pollercategory;
	}


	public void setPollercategory(String pollercategory) {
		this.pollercategory = pollercategory;
	}


	public String getThresholdcategory() {
		return thresholdcategory;
	}


	public void setThresholdcategory(String thresholdcategory) {
		this.thresholdcategory = thresholdcategory;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getManagedobjectinstance() {
		return managedobjectinstance;
	}


	public void setManagedobjectinstance(String managedobjectinstance) {
		this.managedobjectinstance = managedobjectinstance;
	}


	public String getManagedobjecttype() {
		return managedobjecttype;
	}


	public void setManagedobjecttype(String managedobjecttype) {
		this.managedobjecttype = managedobjecttype;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEnable() {
		return enable;
	}


	public void setEnable(String enable) {
		this.enable = enable;
	}


	public String getAutoenable() {
		return autoenable;
	}


	public void setAutoenable(String autoenable) {
		this.autoenable = autoenable;
	}


	public String getConnection() {
		return connection;
	}


	public void setConnection(String connection) {
		this.connection = connection;
	}


	public String getCpu() {
		return cpu;
	}


	public void setCpu(String cpu) {
		this.cpu = cpu;
	}


	public String getRam() {
		return ram;
	}


	public void setRam(String ram) {
		this.ram = ram;
	}


	public String getStoragectrl() {
		return storagectrl;
	}


	public void setStoragectrl(String storagectrl) {
		this.storagectrl = storagectrl;
	}


	public String getHdd1() {
		return hdd1;
	}


	public void setHdd1(String hdd1) {
		this.hdd1 = hdd1;
	}


	public String getHdd2() {
		return hdd2;
	}


	public void setHdd2(String hdd2) {
		this.hdd2 = hdd2;
	}


	public String getHdd3() {
		return hdd3;
	}


	public void setHdd3(String hdd3) {
		this.hdd3 = hdd3;
	}


	public String getHdd4() {
		return hdd4;
	}


	public void setHdd4(String hdd4) {
		this.hdd4 = hdd4;
	}


	public String getHdd5() {
		return hdd5;
	}


	public void setHdd5(String hdd5) {
		this.hdd5 = hdd5;
	}


	public String getHdd6() {
		return hdd6;
	}


	public void setHdd6(String hdd6) {
		this.hdd6 = hdd6;
	}


	public String getNumpowersupplies() {
		return numpowersupplies;
	}


	public void setNumpowersupplies(String numpowersupplies) {
		this.numpowersupplies = numpowersupplies;
	}


	public String getInputpower() {
		return inputpower;
	}


	public void setInputpower(String inputpower) {
		this.inputpower = inputpower;
	}


	public String getAdditionalhardware() {
		return additionalhardware;
	}


	public void setAdditionalhardware(String additionalhardware) {
		this.additionalhardware = additionalhardware;
	}


	public String getAdmin() {
		return admin;
	}


	public void setAdmin(String admin) {
		this.admin = admin;
	}


	public String getSnmpcommunity() {
		return snmpcommunity;
	}


	public void setSnmpcommunity(String snmpcommunity) {
		this.snmpcommunity = snmpcommunity;
	}


	public String getRackunitheight() {
		return rackunitheight;
	}


	public void setRackunitheight(String rackunitheight) {
		this.rackunitheight = rackunitheight;
	}


	
	
	
	
}
