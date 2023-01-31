 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap');
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/shop_main.css">
<link rel="stylesheet" type="text/css" href="resources/css/logo.css">
<link rel="stylesheet" type="text/css" href="resources/css/btn_shop.css">
<style type="text/css">

	.tooltip .tooltiptext {
		  visibility: hidden;
		  width: 150px;
		  background-color: black;
		  color: #fff;
		  text-align: center;
		  border-radius: 6px;
		  padding: 5px 0;
		
		  /* Position the tooltip */
		  position: absolute;
		  z-index: 1000;
		  /*
		  bottom: 100%;
		 left: 50%;
		  margin-left: -50px; */
	}
	
	.tooltip:hover .tooltiptext {
		  visibility: visible;
	}

</style>
<script type="text/javascript">
//던전 정보 보내기
//스테이지 넘어갈때마다 dungeon변수에 +해줘서 불러내야할 던전정보 특정하기
	$(function(){
		$('#cookie').html("보유쿠키: " + cookie);
		$('#total_no').val(skill_point);
		$('#total_no').attr('max', skill_point);
	})
	
	var s_val = [];
   
	$("input:checkbox").click(function() {
	    if ($("input:checkbox:checked").length == 4) {
	       $("input:checkbox:checked").each(function(i,iVal) {
	          $(this).siblings().prop('disabled', false);
	         });
	    }
	 });
   
   function dungeon(){
   
      if ($("input:checkbox:checked").length < 4) {
         alert("스킬은 총 4개를 선택해 주세요!");
         return;
      }
      
      if (document.getElementById('total_no').value != 0) {
         alert("스킬 포인트를 모두 사용해 주세요!");
          return;
      }
      var s_idx = [];
      var s_num = [];

      $("input:checkbox:checked").each(function(i,iVal) {
         s_idx.push($(this).val());
         s_val.push($(this).next().next().val());
         //console.log("스킬포인트: " + s_idx.push($(this).next().val()));
         //console.log($(this).closest($("input[id=skill_no]")));
         s_num.push($(this).attr('name'));
      });

      jQuery.ajaxSettings.traditional = true;   // 이거 추가 안 하면 parameter 넘어갈 때 %5B%5D가 추가되어서 넘어감
      $.ajax({
         url: 'game/dungeon/dungeon.do',
         data: {"stage_val": stage_val, "s_idx": s_idx, "s_num": s_num, "s_val": s_val},
         success: function(res_data){
            $('#disp').html(res_data);
         }
      })
   }
   
   function item_buy(t){
      if(confirm($(t).find('font').text()+"을(를) 구매하시겠습니까?")==false) return;
      var c = Number($(t).find('#price').val());
      console.log("c: " + c);
      if(c > cookie){
    	  
    	  alert("쿠키가 모자랍니다(현재 "+cookie+"개)");
    	  return;
      }
	  cookie -= c;
	  $('#cookie').html("보유쿠키: " + cookie);
      var i_idx=$(t).prop("id");
      $.ajax({
         url: 'game/shop/item_buy.do',
         data: {"i_idx": i_idx},
         success: function(res_data){
            alert(res_data.i_name+"을 구매하였습니다");
            $(t).css('visibility', 'hidden');
            var item_category;
            var level;
            if(res_data.i_level=='일반')
         	  level = $("<font color='white'>"+res_data.i_name+"</font>");
         	if(res_data.i_level=='고급')
         		level = $("<font color='blue'>"+res_data.i_name+"</font>");
         	if(res_data.i_level=='희귀')
         		level = $("<font color='purple'>"+res_data.i_name+"</font>");
         	
            if(res_data.i_category=="머리"){
               $("#helmet").text('');
               $("#helmet").append(level);
               $("#helmet").click(function(){
            	   item_sell(this, 0);
               });
               item_category = $("#helmet");
            }
            else if(res_data.i_category=="상체"){
               $("#upper_body").text('');
               $("#upper_body").append(level);
               $("#upper_body").click(function(){
            	   item_sell(this, 1);
               });
               item_category = $("#upper_body");
            }
            else if(res_data.i_category=="하체"){
               $("#lower_body").text('');
               $("#lower_body").append(level);
               $("#lower_body").click(function(){
            	   item_sell(this, 2);
               });
               item_category = $("#lower_body");
            }
            else if(res_data.i_category=="무기"){
               $("#weapon").text('');
               $("#weapon").append(level);
               $("#weapon").click(function(){
            	   item_sell(this, 3);
               });
               item_category = $("#weapon");
            }
            else{
               $("#potion").text(res_data.i_name);
               item_category = $("#potion");
            }
         
            var span_tag = $("<span class=\"tooltiptext\"><ul></ul></span>");
            $(span_tag).append("<li>"+
                  res_data.i_name+"</li><li>" +
                  res_data.i_class+"</li><li>" +
                  res_data.i_category+"</li><li>" +
                  res_data.i_level+"</li>" );
            if(res_data.i_hp!=0) $(span_tag).append("<li>HP "+res_data.i_hp+" 증가</li>");
            if(res_data.i_hp_percent!=0) $(span_tag).append("<li>HP "+res_data.i_hp_percent+"% 증가</li>");
            if(res_data.i_ad!=0) $(span_tag).append("<li>공격력 "+res_data.i_ad+" 증가</li>");
            if(res_data.i_ad_percent!=0) $(span_tag).append("<li>공격력 "+res_data.i_ad_percent+"% 증가</li>");
            if(res_data.i_ap!=0) $(span_tag).append("<li>주문력 "+res_data.i_ap+" 증가</li>");
            if(res_data.i_ap_percent!=0) $(span_tag).append("<li>주문력 "+res_data.i_ap_percent+"% 증가</li>");
            if(res_data.i_armor!=0) $(span_tag).append("<li>방어력 "+res_data.i_armor+" 증가</li>");
            if(res_data.i_armor_percent!=0) $(span_tag).append("<li>방어력 "+res_data.i_armor_percent+"% 증가</li>");
            if(res_data.i_critical!=0) $(span_tag).append("<li>크리티컬 "+res_data.i_critical+"% 증가</li>");
            if(res_data.i_avd!=0) $(span_tag).append("<li>회피율 "+res_data.i_avd+"% 증가</li>");
            $(span_tag).append("<li id='price' value='"+res_data.i_cookie+"'>판매가격: "+res_data.i_cookie+" cookies </li>");
            item_category.append(span_tag);
         }
      })
   }
   
   function item_sell(t, category){
	   if(confirm('정말 판매하시겠습니까?')==false) return;
	   
	   $.ajax({
		   url:  'game/shop/item_sell.do',
	         data: {"category": category},
	         success: function(res_data){
	        	 cookie += Number($(t).find('#price').val());
	        	 $('#cookie').html("보유쿠키: " + cookie);
	        	 //이름 바꾸고
	        	 //hover 지우기
	        	 if(category==0){
	        	 	$(t).html('머리');
	        	 	$(t).off("click");
	        	 }
	        	 	
	        	 if(category==1){
		        	 $(t).html('상체');
		        	 $(t).off("click");
	        	 }
	        	 if(category==2){
		        	 $(t).html('하체');
		        	 $(t).off("click");
	        	 }
	        	 if(category==3){
		        	 $(t).html('무기');
		        	 $(t).off("click");
	        	 }
	        	 
	        	 
	         }
	   });
   }
   
   function item_shuffle(){
      if(confirm("아이템을 새로고침 하시겠습니까?")==false) return;
      if(10 > cookie){
    	  alert("쿠키가 모자랍니다");
    	  return;
      }
	  cookie -= 10;
	  $('#cookie').html("보유쿠키: " + cookie);
      $.ajax({
         url: 'game/shop/item_shuffle.do',
         data: {"stage_val": stage_val},
         success: function(res_data){
            $('#random1 > div').each(function(index, item){
               $(item).css('visibility', 'visible');
               $(item).attr("id", res_data[index].i_idx);
               $(item).text('');
               /* $(item).click(function(){
            	   item_buy(this, res_data[index].i_cookie);
               }); */
               if(res_data[index].i_level=='일반')
            	   $(item).append("<font color='white'>"+res_data[index].i_name+"</font>");
            	if(res_data[index].i_level=='고급')
            		 $(item).append("<font color='blue'>"+res_data[index].i_name+"</font>");
            	if(res_data[index].i_level=='희귀')
            		 $(item).append("<font color='purple'>"+res_data[index].i_name+"</font>");
            	
            	
               var span_tag = $("<span class=\"tooltiptext\"><ul></ul></span>");
               $(span_tag).append("<li>"+
                     res_data[index].i_name+"</li><li>" +
                     res_data[index].i_class+"</li><li>" +
                     res_data[index].i_category+"</li><li>" +
                     res_data[index].i_level+"</li>" );
               if(res_data[index].i_hp!=0) $(span_tag).append("<li>HP "+res_data[index].i_hp+" 증가</li>");
               if(res_data[index].i_hp_percent!=0) $(span_tag).append("<li>HP "+res_data[index].i_hp_percent+"% 증가</li>");
               if(res_data[index].i_ad!=0) $(span_tag).append("<li>공격력 "+res_data[index].i_ad+" 증가</li>");
               if(res_data[index].i_ad_percent!=0) $(span_tag).append("<li>공격력 "+res_data[index].i_ad_percent+"% 증가</li>");
               if(res_data[index].i_ap!=0) $(span_tag).append("<li>주문력 "+res_data[index].i_ap+" 증가</li>");
               if(res_data[index].i_ap_percent!=0) $(span_tag).append("<li>주문력 "+res_data[index].i_ap_percent+"% 증가</li>");
               if(res_data[index].i_armor!=0) $(span_tag).append("<li>방어력 "+res_data[index].i_armor+" 증가</li>");
               if(res_data[index].i_armor_percent!=0) $(span_tag).append("<li>방어력 "+res_data[index].i_armor_percent+"% 증가</li>");
               if(res_data[index].i_critical!=0) $(span_tag).append("<li>크리티컬 "+res_data[index].i_critical+"% 증가</li>");
               if(res_data[index].i_avd!=0) $(span_tag).append("<li>회피율 "+res_data[index].i_avd+"% 증가</li>");
               $(span_tag).append("<li id='price' value='"+res_data[index].i_cookie+"'>판매가격: "+res_data[index].i_cookie+" cookies </li>");
               $(item).append(span_tag);
            });
         }
      })
   }
</script>
<script type="text/javascript">
$("input:checkbox").click(function() {
    
    var max = $("input:checkbox:checked").length >= 4;    
    
       $("input:checkbox").not(":checked").attr("disabled",max);
    });
   
    var Myelement = document.getElementById('total_no');
    
    $("input:checkbox").change(function() {
       
       $(this).siblings().prop('disabled', false);
       
       
       if (Myelement.value == 0) {
          alert('스킬 포인트를 모두 소진하였습니다.');
          $(this).prop("checked", false);
       } else {
          Myelement.value = Number(Myelement.value) - 1;
          Number($(this).next().next().val(Number($(this).next().next().val()) + 1));
       }
       
       

        $("input:checkbox").not(":checked").each(function() {
           
           $(this).siblings().prop('disabled', true);
           

             if ($(this).next().next().val() > 0) {
                //Myelement.value = Number(Myelement.value) + 1;
                var temp = 0;
                temp = Number($(this).next().next().val());
                console.log(temp);
                console.log(Number(Myelement.value));
                Myelement.value = Number(Myelement.value) + temp;
                $(this).next().next().val(0);
             }

        });

   });
    
    $("input[type=number]").bind('keyup input', function(){
       var num = 0;
       var t_num = 0;
       
       if($(this).val()==0){
    	   $(this).prop('disabled', true);
    	   $(this).prev().prev().prop("checked", false); 
    	   var ma = $("input:checkbox:checked").length >= 4;    
           $("input:checkbox").not(":checked").attr("disabled",ma);
       }
       
       for (var a of $("input[id=skill_no]")) {
           t_num = 0;
           t_num = Number($(a).val());
           //s_val.push(t_num);
           num = num + Number($(a).val());
        }
      
       Myelement.value = skill_point - num;
       
       if (Myelement.value < 0) {
           Myelement.value++;
           $(this).val($(this).val() - 1);
           alert('스킬포인트를 모두 소진하였습니다.');
           return false;
        }
       
       
       ////////////////스킬설명//////////////////////////////////////////////
       var skill_num = Number( $(this).prev().prev().attr('name') );
       var damage;
       if(skill_num==1){
          damage = ${ main_ch.skill_vo[0].s_basic_damage }
                      +${ main_ch.skill_vo[0].s_add_damage } * $(this).val()
                      +${ main_ch.c_ad } * ${ main_ch.skill_vo[0].s_coeff_ad }
                      +${ main_ch.c_ap } * ${ main_ch.skill_vo[0].s_coeff_ap };
       }else  if(skill_num==2){
          damage = ${ main_ch.skill_vo[1].s_basic_damage }
                   +${ main_ch.skill_vo[1].s_add_damage } * $(this).val()
                   +${ main_ch.c_ad } * ${ main_ch.skill_vo[1].s_coeff_ad }
                   +${ main_ch.c_ap } * ${ main_ch.skill_vo[1].s_coeff_ap };
     }else  if(skill_num==3){
          damage = ${ main_ch.skill_vo[2].s_basic_damage }
          +${ main_ch.skill_vo[2].s_add_damage } * $(this).val()
          +${ main_ch.c_ad } * ${ main_ch.skill_vo[2].s_coeff_ad }
          +${ main_ch.c_ap } * ${ main_ch.skill_vo[2].s_coeff_ap };
     }else  if(skill_num==4){
          damage = ${ main_ch.skill_vo[3].s_basic_damage }
          +${ main_ch.skill_vo[3].s_add_damage } * $(this).val()
          +${ main_ch.c_ad } * ${ main_ch.skill_vo[3].s_coeff_ad }
          +${ main_ch.c_ap } * ${ main_ch.skill_vo[3].s_coeff_ap };
     }/* 버프류는 다시 생각해봐야함
     else  if(skill_num==5){
          damage = ${ main_ch.skill_vo[4].s_basic_damage }
          +${ main_ch.skill_vo[4].s_add_damage } * $(this).val()
          +${ main_ch.c_ad } * ${ main_ch.skill_vo[4].s_coeff_ad }
          +${ main_ch.c_ap } * ${ main_ch.skill_vo[4].s_coeff_ap };
     }else  if(skill_num==6){
          damage = ${ main_ch.skill_vo[5].s_basic_damage }
          +${ main_ch.skill_vo[5].s_add_damage } * $(this).val()
          +${ main_ch.c_ad } * ${ main_ch.skill_vo[5].s_coeff_ad }
          +${ main_ch.c_ap } * ${ main_ch.skill_vo[5].s_coeff_ap };
     }else  if(skill_num==7){
          damage = ${ main_ch.skill_vo[6].s_basic_damage }
          +${ main_ch.skill_vo[6].s_add_damage } * $(this).val()
          +${ main_ch.c_ad } * ${ main_ch.skill_vo[6].s_coeff_ad }
          +${ main_ch.c_ap } * ${ main_ch.skill_vo[6].s_coeff_ap };
     }else  if(skill_num==8){
          damage = ${ main_ch.skill_vo[7].s_basic_damage }
          +${ main_ch.skill_vo[7].s_add_damage } * $(this).val()
          +${ main_ch.c_ad } * ${ main_ch.skill_vo[7].s_coeff_ad }
          +${ main_ch.c_ap } * ${ main_ch.skill_vo[7].s_coeff_ap };
     } */
       
       var str1 = $(this).prev().find('span').html().split("의 데미지")[0];
       var str2 = $(this).prev().find('span').html().split("의 데미지")[1];
       var str = str1.replace( str1.substring( str1.lastIndexOf(" ")+1, ) , damage);
      console.log(str);
      console.log(str2);
       $(this).prev().find('span').html(str+"의 데미지" +str2);
       //////////////////////////////////////////////////////////////////////
    });
    Myelement = document.getElementById('total_no');
</script>
</head>
<body>

<div id="box">
<!------------------------------------------------------------------------>
	<div id="header">
		<a href="index.jsp">
			<img id="logo" src="resources/img/logo.png">
		</a>						
	</div>
<!------------------------------------------------------------------------>
	<div id="content">
         <div id="shop_table">
            <table border="2">
               <tr id="skill_point">
                  <td colspan="10">스킬포인트: <input type="number" id="total_no" name="total_no" min="0" max="10" value="10" readonly="readonly"></td>
               </tr>
               <tr>
                  <td colspan="10" id="skill_check">
                     <div id="skill_left">
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[0].s_num }" value="${ main_ch.skill_vo[0].s_idx }" >
                           <span class="tooltip" >
                           ${ main_ch.skill_vo[0].s_name }
                           <span class="tooltiptext">${ main_ch.skill_vo[0].s_info }</span>
                        </span>
                           <input type="number" class="s_n" id="skill_no" name="skill_no1" min="0" max="10" value="0" disabled><br><br>
                        </div>
                        
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[2].s_num }" value="${ main_ch.skill_vo[2].s_idx }" >
                           <span class="tooltip" >
                           ${ main_ch.skill_vo[2].s_name }
                           <span class="tooltiptext">${ main_ch.skill_vo[2].s_info }</span>
                        </span>
                        <input type="number" class="s_n" id="skill_no" name="skill_no2" min="0" max="10" value="0" disabled><br><br>
                        </div>
                        
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[4].s_num }" value="${ main_ch.skill_vo[4].s_idx }" >
                           	<span class="tooltip" >
	                           ${ main_ch.skill_vo[4].s_name }
	                           <span class="tooltiptext">${ main_ch.skill_vo[4].s_info }</span>
	                        </span>
                        <input type="number" class="s_n" id="skill_no" name="skill_no3" min="0" max="10" value="0" disabled><br><br>
                        </div>
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[6].s_num }" value="${ main_ch.skill_vo[6].s_idx }" >
	                         <span class="tooltip" >
	                           ${ main_ch.skill_vo[6].s_name }
	                           <span class="tooltiptext">${ main_ch.skill_vo[6].s_info }</span>
	                        </span>
                        <input type="number" class="s_n" id="skill_no" name="skill_no4" min="0" max="10" value="0" disabled><br><br>
                        </div>
                     </div>
                     
                     <div id="skill_right">
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[1].s_num }" value="${ main_ch.skill_vo[1].s_idx }" >
	                         <span class="tooltip" >
	                           ${ main_ch.skill_vo[1].s_name }
	                           <span class="tooltiptext">${ main_ch.skill_vo[1].s_info }</span>
	                        </span>
                        <input type="number" id="skill_no" name="skill_no5" min="0" max="10" value="0" disabled><br><br>
                        </div>   
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[3].s_num }" value="${ main_ch.skill_vo[3].s_idx }" >
	                         <span class="tooltip" >
	                           ${ main_ch.skill_vo[3].s_name }
	                           <span class="tooltiptext">${ main_ch.skill_vo[3].s_info }</span>
	                        </span>
                        <input type="number" id="skill_no" name="skill_no6" min="0" max="10" value="0" disabled><br><br>
                        </div>
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[5].s_num }" value="${ main_ch.skill_vo[5].s_idx }" >
	                         <span class="tooltip" >
	                           ${ main_ch.skill_vo[5].s_name }
	                           <span class="tooltiptext">${ main_ch.skill_vo[5].s_info }</span>
	                        </span>
                        <input type="number" id="skill_no" name="skill_no7" min="0" max="10" value="0" disabled><br><br>
                        </div>
                        <div>
                           <input id="skill" type="checkbox" name="${ main_ch.skill_vo[7].s_num }" value="${ main_ch.skill_vo[7].s_idx }" >
	                         <span class="tooltip" >
	                           ${ main_ch.skill_vo[7].s_name }
	                           <span class="tooltiptext">${ main_ch.skill_vo[7].s_info }</span>
	                        </span>
                        <input type="number" id="skill_no" name="skill_no8" min="0" max="10" value="0" disabled><br><br>
                        </div>   
                     </div>
                  </td>
               </tr>
<!--------------------------------------------------------------------------------------------------------------------------->
					<tr id="inventory">
                     <c:if test="${ not empty main_ch.item_list[0].i_name }">
                        <td id="helmet" class="tooltip" onclick="item_sell(this, 0);">
                        
                        <c:if test="${ main_ch.item_list[0].i_level  eq '일반'}">
                        	<font color="white">${ main_ch.item_list[0].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[0].i_level  eq '고급'}">
                        	<font color="blue">${ main_ch.item_list[0].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[0].i_level  eq '희귀'}">
                        	<font color="purple">${ main_ch.item_list[0].i_name }</font>
                        </c:if>
                        
                        <span class="tooltiptext">
                        <ul>
                           <li>${ main_ch.item_list[0].i_name }</li>
                           <li>${ main_ch.item_list[0].i_class }</li>
                           <li>${ main_ch.item_list[0].i_category } </li>
                           <li>${ main_ch.item_list[0].i_level } </li>
                           <c:if test="${ not (main_ch.item_list[0].i_hp eq 0)}">
                              <li>HP ${ main_ch.item_list[0].i_hp } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_hp_percent eq 0)}">
                              <li>HP ${ main_ch.item_list[0].i_hp_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_ad eq 0)}">
                              <li>공격력 ${ main_ch.item_list[0].i_ad } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_ad_percent eq 0)}">
                              <li>공격력 ${ main_ch.item_list[0].i_ad_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_ap eq 0)}">
                              <li>주문력 ${ main_ch.item_list[0].i_ap } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_ap_percent eq 0)}">
                              <li>주문력 ${ main_ch.item_list[0].i_ap_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_armor eq 0)}">
                              <li>방어력 ${ main_ch.item_list[0].i_armor } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_armor_percent eq 0)}">
                              <li>방어력 ${ main_ch.item_list[0].i_armor_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_critical eq 0)}">
                              <li>크리티컬 ${ main_ch.item_list[0].i_critical }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[0].i_avd eq 0)}">
                              <li>회피율 ${ main_ch.item_list[0].i_avd }% 증가</li>
                           </c:if>
                           		<li id="price" value="${ main_ch.item_list[0].i_cookie }">판매가격: ${ main_ch.item_list[0].i_cookie } cookies </li>
                        </ul>
                     </span>
                     </td>
                     </c:if>
                     <c:if test="${ empty main_ch.item_list[0].i_name}">
                        <td id="helmet" class="tooltip" >머리</td>
                     </c:if>
                     
                     <c:if test="${ not empty main_ch.item_list[1].i_name }">
                        <td id="upper_body" class="tooltip" onclick="item_sell(this, 1);">
                        
						<c:if test="${ main_ch.item_list[1].i_level  eq '일반'}">
                        	<font color="white">${ main_ch.item_list[1].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[1].i_level  eq '고급'}">
                        	<font color="blue">${ main_ch.item_list[1].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[1].i_level  eq '희귀'}">
                        	<font color="purple">${ main_ch.item_list[1].i_name }</font>
                        </c:if>
                        
                        <span class="tooltiptext">
                        <ul>
                           <li>${ main_ch.item_list[1].i_name }</li>
                           <li>${ main_ch.item_list[1].i_class }</li>
                           <li>${ main_ch.item_list[1].i_category } </li>
                           <li>${ main_ch.item_list[1].i_level } </li>
                           <c:if test="${ not (main_ch.item_list[1].i_hp eq 0)}">
                              <li>HP ${ main_ch.item_list[1].i_hp } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_hp_percent eq 0)}">
                              <li>HP ${ main_ch.item_list[1].i_hp_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_ad eq 0)}">
                              <li>공격력 ${ main_ch.item_list[1].i_ad } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_ad_percent eq 0)}">
                              <li>공격력 ${ main_ch.item_list[1].i_ad_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_ap eq 0)}">
                              <li>주문력 ${ main_ch.item_list[1].i_ap } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_ap_percent eq 0)}">
                              <li>주문력 ${ main_ch.item_list[1].i_ap_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_armor eq 0)}">
                              <li>방어력 ${ main_ch.item_list[1].i_armor } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_armor_percent eq 0)}">
                              <li>방어력 ${ main_ch.item_list[1].i_armor_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_critical eq 0)}">
                              <li>크리티컬 ${ main_ch.item_list[1].i_critical }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[1].i_avd eq 0)}">
                              <li>회피율 ${ main_ch.item_list[1].i_avd }% 증가</li>
                           </c:if>
                           		<li id="price" value="${ main_ch.item_list[1].i_cookie }">판매가격: ${ main_ch.item_list[1].i_cookie } cookies </li>
                        </ul>
                     </span>
                     </td>
                     </c:if>
                     <c:if test="${ empty main_ch.item_list[1].i_name}">
                        <td id="upper_body" class="tooltip">상체</td>
                     </c:if>
                     
                     <c:if test="${ not empty main_ch.item_list[2].i_name }">
                        <td id="lower_body" class="tooltip" onclick="item_sell(this, 2);">

						<c:if test="${ main_ch.item_list[2].i_level  eq '일반'}">
                        	<font color="white">${ main_ch.item_list[2].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[2].i_level  eq '고급'}">
                        	<font color="blue">${ main_ch.item_list[2].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[2].i_level  eq '희귀'}">
                        	<font color="purple">${ main_ch.item_list[2].i_name }</font>
                        </c:if>
                        
                        <span class="tooltiptext">
                        <ul>
                           <li>${ main_ch.item_list[2].i_name }</li>
                           <li>${ main_ch.item_list[2].i_class }</li>
                           <li>${ main_ch.item_list[2].i_category } </li>
                           <li>${ main_ch.item_list[2].i_level } </li>
                           <c:if test="${ not (main_ch.item_list[2].i_hp eq 0)}">
                              <li>HP ${ main_ch.item_list[2].i_hp } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_hp_percent eq 0)}">
                              <li>HP ${ main_ch.item_list[2].i_hp_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_ad eq 0)}">
                              <li>공격력 ${ main_ch.item_list[2].i_ad } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_ad_percent eq 0)}">
                              <li>공격력 ${ main_ch.item_list[2].i_ad_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_ap eq 0)}">
                              <li>주문력 ${ main_ch.item_list[2].i_ap } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_ap_percent eq 0)}">
                              <li>주문력 ${ main_ch.item_list[2].i_ap_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_armor eq 0)}">
                              <li>방어력 ${ main_ch.item_list[2].i_armor } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_armor_percent eq 0)}">
                              <li>방어력 ${ main_ch.item_list[2].i_armor_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_critical eq 0)}">
                              <li>크리티컬 ${ main_ch.item_list[2].i_critical }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[2].i_avd eq 0)}">
                              <li>회피율 ${ main_ch.item_list[2].i_avd }% 증가</li>
                           </c:if>
                           		<li id="price" value="${ main_ch.item_list[2].i_cookie }">판매가격: ${ main_ch.item_list[2].i_cookie } cookies </li>
                        </ul>
                     </span>
                     </td>
                     </c:if>
                     <c:if test="${ empty main_ch.item_list[2].i_name}">
                        <td id="lower_body" class="tooltip">하체</td>
                     </c:if>
                     
                     <c:if test="${ not empty main_ch.item_list[3].i_name }">
                        <td id="weapon" class="tooltip" onclick="item_sell(this, 3);">

						<c:if test="${ main_ch.item_list[3].i_level  eq '일반'}">
                        	<font color="white">${ main_ch.item_list[3].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[3].i_level  eq '고급'}">
                        	<font color="blue">${ main_ch.item_list[3].i_name }</font>
                        </c:if>
                        <c:if test="${ main_ch.item_list[3].i_level  eq '희귀'}">
                        	<font color="purple">${ main_ch.item_list[3].i_name }</font>
                        </c:if>
                        
                        <span class="tooltiptext">
                        <ul>
                           <li>${ main_ch.item_list[3].i_name }</li>
                           <li>${ main_ch.item_list[3].i_class }</li>
                           <li>${ main_ch.item_list[3].i_category } </li>
                           <li>${ main_ch.item_list[3].i_level } </li>
                           <c:if test="${ not (main_ch.item_list[3].i_hp eq 0)}">
                              <li>HP ${ main_ch.item_list[3].i_hp } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_hp_percent eq 0)}">
                              <li>HP ${ main_ch.item_list[3].i_hp_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_ad eq 0)}">
                              <li>공격력 ${ main_ch.item_list[3].i_ad } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_ad_percent eq 0)}">
                              <li>공격력 ${ main_ch.item_list[3].i_ad_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_ap eq 0)}">
                              <li>주문력 ${ main_ch.item_list[3].i_ap } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_ap_percent eq 0)}">
                              <li>주문력 ${ main_ch.item_list[3].i_ap_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_armor eq 0)}">
                              <li>방어력 ${ main_ch.item_list[3].i_armor } 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_armor_percent eq 0)}">
                              <li>방어력 ${ main_ch.item_list[3].i_armor_percent }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_critical eq 0)}">
                              <li>크리티컬 ${ main_ch.item_list[3].i_critical }% 증가</li>
                           </c:if>
                           <c:if test="${ not (main_ch.item_list[3].i_avd eq 0)}">
                              <li>회피율 ${ main_ch.item_list[3].i_avd }% 증가</li>
                           </c:if>
                           	<li id="price" value="${ main_ch.item_list[3].i_cookie }">판매가격: ${ main_ch.item_list[3].i_cookie } cookies </li>
                        </ul>
                     </span>
                     </td>
                     </c:if>
                     <c:if test="${ empty main_ch.item_list[3].i_name}">
                        <td id="weapon" class="tooltip">무기</td>
                     </c:if>
                     
                  <td  id="potion" class="tooltip">물약</td>
               </tr>
            </table>
         </div>
			

			<div id="random_items">
				<div id="random_back" style="background-image: url('resources/img/shop_back.png');">
					<div id="cookie" style="text-align: center;"></div>
					<div id="random1">
					<br><br><br><br><br>
						<div id="${ selected_item_list[0].i_idx }" class="tooltip" onclick="item_buy(this)">
						
						<c:if test="${ selected_item_list[0].i_level  eq '일반'}">
                        	<font color="white">${ selected_item_list[0].i_name }</font>
                        </c:if>
                        <c:if test="${ selected_item_list[0].i_level  eq '고급'}">
                        	<font color="blue">${ selected_item_list[0].i_name }</font>
                        </c:if>
                        <c:if test="${ selected_item_list[0].i_level  eq '희귀'}">
                        	<font color="purple">${ selected_item_list[0].i_name }</font>
                        </c:if>
                        
						<br>
							<span class="tooltiptext">
								<ul>
									<li>${ selected_item_list[0].i_name }</li>
									<li>${ selected_item_list[0].i_class }</li>
									<li>${ selected_item_list[0].i_category } </li>
									<li>${ selected_item_list[0].i_level } </li>
									<c:if test="${ not (selected_item_list[0].i_hp eq 0)}">
										<li>HP ${ selected_item_list[0].i_hp } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_hp_percent eq 0)}">
										<li>HP ${ selected_item_list[0].i_hp_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_ad eq 0)}">
										<li>공격력 ${ selected_item_list[0].i_ad } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_ad_percent eq 0)}">
										<li>공격력 ${ selected_item_list[0].i_ad_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_ap eq 0)}">
										<li>주문력 ${ selected_item_list[0].i_ap } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_ap_percent eq 0)}">
										<li>주문력 ${ selected_item_list[0].i_ap_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_armor eq 0)}">
										<li>방어력 ${ selected_item_list[0].i_armor } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_armor_percent eq 0)}">
										<li>방어력 ${ selected_item_list[0].i_armor_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_critical eq 0)}">
										<li>크리티컬 ${ selected_item_list[0].i_critical }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[0].i_avd eq 0)}">
										<li>회피율 ${ selected_item_list[0].i_avd }% 증가</li>
									</c:if>
									<li id="price" value="${ selected_item_list[0].i_cookie }"> 가격 ${ selected_item_list[0].i_cookie } cookies </li>
								</ul>
							</span>
						</div>
						<div id="${ selected_item_list[1].i_idx }"  class="tooltip" onclick="item_buy(this)">
						<c:if test="${ selected_item_list[1].i_level  eq '일반'}">
                        	<font color="white">${ selected_item_list[1].i_name }</font>
                        </c:if>
                        <c:if test="${ selected_item_list[1].i_level  eq '고급'}">
                        	<font color="blue">${ selected_item_list[1].i_name }</font>
                        </c:if>
                        <c:if test="${ selected_item_list[1].i_level  eq '희귀'}">
                        	<font color="purple">${ selected_item_list[1].i_name }</font>
                        </c:if>
						
						<br>
							<span class="tooltiptext">
								<ul>
									<li>${ selected_item_list[1].i_name }</li>
									<li>${ selected_item_list[1].i_class }</li>
									<li>${ selected_item_list[1].i_category } </li>
									<li>${ selected_item_list[1].i_level } </li>
									<c:if test="${ not (selected_item_list[1].i_hp eq 0)}">
										<li>HP ${ selected_item_list[1].i_hp } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_hp_percent eq 0)}">
										<li>HP ${ selected_item_list[1].i_hp_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_ad eq 0)}">
										<li>공격력 ${ selected_item_list[1].i_ad } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_ad_percent eq 0)}">
										<li>공격력 ${ selected_item_list[1].i_ad_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_ap eq 0)}">
										<li>주문력 ${ selected_item_list[1].i_ap } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_ap_percent eq 0)}">
										<li>주문력 ${ selected_item_list[1].i_ap_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_armor eq 0)}">
										<li>방어력 ${ selected_item_list[1].i_armor } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_armor_percent eq 0)}">
										<li>방어력 ${ selected_item_list[1].i_armor_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_critical eq 0)}">
										<li>크리티컬 ${ selected_item_list[1].i_critical }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[1].i_avd eq 0)}">
										<li>회피율 ${ selected_item_list[1].i_avd }% 증가</li>
									</c:if>
									<li id="price" value="${ selected_item_list[1].i_cookie }"> 가격 ${ selected_item_list[1].i_cookie } cookies </li>
								</ul>
							</span>
						</div>
						<div id="${ selected_item_list[2].i_idx }"  class="tooltip" onclick="item_buy(this)">
						
						<c:if test="${ selected_item_list[2].i_level  eq '일반'}">
                        	<font color="white">${ selected_item_list[2].i_name }</font>
                        </c:if>
                        <c:if test="${ selected_item_list[2].i_level  eq '고급'}">
                        	<font color="blue">${ selected_item_list[2].i_name }</font>
                        </c:if>
                        <c:if test="${ selected_item_list[2].i_level  eq '희귀'}">
                        	<font color="purple">${ selected_item_list[2].i_name }</font>
                        </c:if>
						<br>
							<span class="tooltiptext">
								<ul>
									<li>${ selected_item_list[2].i_name }</li>
									<li>${ selected_item_list[2].i_class }</li>
									<li>${ selected_item_list[2].i_category } </li>
									<li>${ selected_item_list[2].i_level } </li>
									<c:if test="${ not (selected_item_list[2].i_hp eq 0)}">
										<li>HP ${ selected_item_list[2].i_hp } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_hp_percent eq 0)}">
										<li>HP ${ selected_item_list[2].i_hp_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_ad eq 0)}">
										<li>공격력 ${ selected_item_list[2].i_ad } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_ad_percent eq 0)}">
										<li>공격력 ${ selected_item_list[2].i_ad_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_ap eq 0)}">
										<li>주문력 ${ selected_item_list[2].i_ap } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_ap_percent eq 0)}">
										<li>주문력 ${ selected_item_list[2].i_ap_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_armor eq 0)}">
										<li>방어력 ${ selected_item_list[2].i_armor } 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_armor_percent eq 0)}">
										<li>방어력 ${ selected_item_list[2].i_armor_percent }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_critical eq 0)}">
										<li>크리티컬 ${ selected_item_list[2].i_critical }% 증가</li>
									</c:if>
									<c:if test="${ not (selected_item_list[2].i_avd eq 0)}">
										<li>회피율 ${ selected_item_list[2].i_avd }% 증가</li>
									</c:if>
									<li id="price" value="${ selected_item_list[2].i_cookie }"> 가격 ${ selected_item_list[2].i_cookie } cookies </li>
								</ul>
							</span>
							</div><br><br><br>
							
							<div id="buttons" style="margin-left: 60px;">
								<button class="button button--pipaluk button--inverted button--round-l button--text-thick button--text-upper" id="again" onclick="item_shuffle();">아이템 리셋</button>
								<button class="button button--pipaluk button--inverted button--round-l button--text-thick button--text-upper" id="next_level" onclick="dungeon();">다음 스테이지</button>
							</div>
					
					<div id="clear"></div>
				</div>
			</div>
		</div> <!-- random_back end  -->
	</div>	
<!------------------------------------------------------------------------>
	<div id = "footer">
		<p id="copyright">Copyright 2022.Chasing Paws All rights reserved.</p>
	</div>
<!------------------------------------------------------------------------>	
</div>	

</body>
</html>