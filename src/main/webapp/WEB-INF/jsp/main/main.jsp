<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="calendar" class="mt-4"></div>
<script>
    $(document).ready(function() {
    $('#calendar').fullCalendar({
        header: {
        left: 'prev',
        center: 'title',
        right: 'next'
        },
        defaultView: 'month',
       // navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: '/todo/todo-calendar'
        	/*function(start, end, callback) {
        	$.ajax({
    		    url: '/todo/todo-calendar',
    		    dataType: 'json',
    		    success: function(data, text, request) {
    		    	var events = eval(data);
    		        callback(events);
    		    }
    		});
    	}*/
    
       // events: [{                      
       // title : "발길향하는 공간"                   
       // url : "http://www.wickedmiso.com/"                    
       // start : "2024-03-25"}]
        
    });
    });
</script>