$(document).ready(function(){
	
	$('#myModal').on('shown.bs.modal', function (e) {
			var thisId = e.relatedTarget.id;
			
			if(thisId == 'map_gz'){
				$("#myModalLabel").text("ZAKER广州总部");
				// 百度地图API功能
				var map = new BMap.Map('allmap');
				var poi = new BMap.Point(113.35097,23.148671);
				map.centerAndZoom(poi, 16);
				map.enableScrollWheelZoom();
		
				var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
				                '地址：广州市天河区五山路261号省农机所ZAKER荷花庭院<br/>电话：020-38465849' +
				              '</div>';
		
				//创建检索信息窗口对象
				var searchInfoWindow = null;
				searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
						title  : "ZAKER广州总部",      //标题
						width  : 290,             //宽度
						height : 105,              //高度
						panel  : "panel",         //检索结果面板
						enableAutoPan : true,     //自动平移
						searchTypes   :[
							BMAPLIB_TAB_SEARCH,   //周边检索
							BMAPLIB_TAB_TO_HERE,  //到这里去
							BMAPLIB_TAB_FROM_HERE //从这里出发
						]
					});
				var marker = new BMap.Marker(poi); //创建marker对象
				marker.enableDragging(); //marker可拖拽
				marker.addEventListener("click", function(e){
				    searchInfoWindow.open(marker);
				})
				map.addOverlay(marker); //在地图中添加marker
			}else if(thisId == 'map_bj'){
				$("#myModalLabel").text("ZAKER北京分公司");
				// 百度地图API功能
				var map = new BMap.Map('allmap');
				var poi = new BMap.Point(116.340482,39.990223);
				map.centerAndZoom(poi, 16);
				map.enableScrollWheelZoom();
		
				var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
//				                '<img src="../img/baidu.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
				                '地址：北京市海淀区中关村东路66号世纪科贸大夏B座2303室<br/>电话：010-62670939' +
				              '</div>';
		
				//创建检索信息窗口对象
				var searchInfoWindow = null;
				searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
						title  : "ZAKER北京分公司",      //标题
						width  : 290,             //宽度
						height : 105,              //高度
						panel  : "panel",         //检索结果面板
						enableAutoPan : true,     //自动平移
						searchTypes   :[
							BMAPLIB_TAB_SEARCH,   //周边检索
							BMAPLIB_TAB_TO_HERE,  //到这里去
							BMAPLIB_TAB_FROM_HERE //从这里出发
						]
					});
				var marker = new BMap.Marker(poi); //创建marker对象
				marker.enableDragging(); //marker可拖拽
				marker.addEventListener("click", function(e){
				    searchInfoWindow.open(marker);
				})
				map.addOverlay(marker); //在地图中添加marker
			}else if(thisId == 'map_sh'){
				$("#myModalLabel").text("ZAKER上海分公司");
				// 百度地图API功能
				var map = new BMap.Map('allmap');
				var poi = new BMap.Point(121.488588,31.208677);
				map.centerAndZoom(poi, 16);
				map.enableScrollWheelZoom();
		
				var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
//				                '<img src="../img/baidu.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
				                '地址：上海市黄浦区局门路458号7号楼7601室' +
				              '</div>';
		
				//创建检索信息窗口对象
				var searchInfoWindow = null;
				searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
						title  : "ZAKER上海分公司",      //标题
						width  : 290,             //宽度
						height : 105,              //高度
						panel  : "panel",         //检索结果面板
						enableAutoPan : true,     //自动平移
						searchTypes   :[
							BMAPLIB_TAB_SEARCH,   //周边检索
							BMAPLIB_TAB_TO_HERE,  //到这里去
							BMAPLIB_TAB_FROM_HERE //从这里出发
						]
					});
				var marker = new BMap.Marker(poi); //创建marker对象
				marker.enableDragging(); //marker可拖拽
				marker.addEventListener("click", function(e){
				    searchInfoWindow.open(marker);
				})
				map.addOverlay(marker); //在地图中添加marker
			}
			
			//修改样式
			$("#BMapLib_stationText0").addClass("search_first_td");
			$(".BMapLib_nav_tab_content li:last-child td:last-child").addClass("search_last_td");

		});
});