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
        eventLimit: true // allow "more" link when too many events
    });
    });
</script>