function mySelect(){};
mySelect.prototype={
	sel_left:".select_left",
	sel_right:".select_right",
	sel_input:".select_input",
	movetoright:function(){
		var sel_left=$(this.sel_left);			
		var sel_right=$(this.sel_right);
		var select_html="";
		if(sel_left.find("option:selected").size()>0){
			res = new Array();
			sel_left.find("option:selected").each(function(i){
				 select_html+="<option value='"+sel_left.find("option:selected").eq(i).attr("value")+"'>"+sel_left.find("option:selected").eq(i).html()+"</option>";
				res[i] = sel_left.find("option:selected").eq(i).val();
			});
			sel_right.append(select_html);
			sel_left.find("option:selected").remove();
			this.hiddenvalue();

		}else{
			this.warning();
		}
	},
	movetoleft:function(){
		var sel_left=$(this.sel_left);
		var sel_right=$(this.sel_right);
		var select_html="";
		if(sel_right.find("option:selected").size()>0){
			sel_right.find("option:selected").each(function(i){
				 select_html+="<option value='"+sel_right.find("option:selected").eq(i).attr("value")+"'>"+sel_right.find("option:selected").eq(i).html()+"</option>";
			});
			sel_left.append(select_html);
			sel_right.find("option:selected").remove();
			this.hiddenvalue();
		}else{
			this.warning();
		}
	},

	hiddenvalue:function(){
		var sel_right=$(this.sel_right);
		var _cnt = sel_right.find("option").size();
		var res = new Array();

		for (var i=0; i<_cnt; i++) {
			res[i] = sel_right.find("option").eq(i).val();
          }

		  res=res.join("|");

		  $(this.sel_input).val(res);


	},



	top:function(){
			var _select=$(this.sel_right);
			var _selected=_select.find("option:selected");
			var select_html="";
			if(_selected.size()>0){
				_selected.each(function(i){
					select_html+="<option selected='selected' value='"+_selected.eq(i).attr("value")+"'>"+_selected.eq(i).html()+"</option>"
				});
				_select.prepend(select_html);
				_selected.remove();
			}else{
				alert("선택하세요!");
			}
			this.hiddenvalue();
		},

	up:function(select_tag){
			var _select=$(this.sel_right);
			var _selected=_select.find("option:selected");
			var _selectedSize=_selected.size();
			var select_html="";
			if(_selectedSize==1){
				if(_selected.prevAll().size()>0){
					var prev_html="<option value='"+_selected.prev().attr("value")+"'>"+_selected.prev().html()+"</option>";
					var selected_html="<option selected='selected' value='"+_selected.attr("value")+"'>"+_selected.html()+"</option>";
					_selected.prev().remove();
					_selected.replaceWith(selected_html+prev_html);
					this.hiddenvalue();
				}else{
					alert("최상단 입니다.");
				}
			}else{
				alert("정렬을 선택하시기 바랍니다!");
			}
		},
		down:function(){
			var _select=$(this.sel_right);
			var _selected=_select.find("option:selected");
			var _selectedSize=_selected.size();
			var select_html="";
			if(_selectedSize==1){
				if(_selected.nextAll().size()>0){
					var next_html="<option value='"+_selected.next().attr("value")+"'>"+_selected.next().html()+"</option>";
					var selected_html="<option selected='selected' value='"+_selected.attr("value")+"'>"+_selected.html()+"</option>";
					_selected.next().remove();
					_selected.replaceWith(next_html+selected_html);
					this.hiddenvalue();
				}else{
					alert("마지막입니다!");
				}
			}else{
				alert("정렬을 선택하시기 바랍니다!");
			}
		},
		bottom:function(){
			var _select=$(this.sel_right);
			var _selected=_select.find("option:selected");
			var select_html="";
			if(_selected.size()>0){
				_selected.each(function(i){
					select_html+="<option selected='selected' value='"+_selected.eq(i).attr("value")+"'>"+_selected.eq(i).html()+"</option>"
				});
				_select.append(select_html);
				_selected.remove();
				this.hiddenvalue();
			}else{
				alert("선택하세요!");
			}
		},

	call:function(){//
	},
	warning:function(){//없을 경우는 false를 반환할지 여부를 선택.
		alert("선택하세요!");
	}
};