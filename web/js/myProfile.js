function setUsername(userIdCurrentlyLoggedIn) {
    $.ajax({
        type: "GET",
        url: "http://147.83.7.207:8080/pokeetac/user/" + userIdCurrentlyLoggedIn,
        contentType: "application/json",
        success: [
            function (data) {
                $("#usernameWelcome").html("Welcome " + data.username + "!");
            }
        ],
        error: [
            function (request, status) {
                alert(status);
            }
        ]
    });
}

function setUserLevel(userIdCurrentlyLoggedIn) {
    $.ajax({
        type: "GET",
        url: "http://147.83.7.207:8080/pokeetac/user/level/" + userIdCurrentlyLoggedIn,
        contentType: "application/json",
        success: [
            function (data) {
                $("#userLevelDiv").html(data.userLevel);
            }
        ],
        error: [
            function (request, status) {
                alert(status);
            }
        ]
    });
}

function fillProfemonsCarousel(userIdCurrentlyLoggedIn) {
    $.ajax({
        type: "GET",
        url: "http://147.83.7.207:8080/pokeetac/user/profemons/" + userIdCurrentlyLoggedIn,
        contentType: "application/json",
        success: [
            function (data) {
                $.each(data, function (i, item) {
                    $("#carousel-indicators").append("<li id='#slide" + i + "' data-target='#myCapturadosCarousel' data-slide-to='" + i + "'></li>");
                    $("#carousel-inner").append("<div id='item" + i + "' class='item'>" +
                        "<img src='/images/profedex/" + data[i].name + ".png' alt='" + data[i].name + "'>" +
                        "<div class='carousel-caption'>" +
                        "<h3>" + data[i].name + "</h3>" +
                        "<p>Level: " + data[i].level + "</p>" +
                        "</div>" +
                        "</div>");
                    if (i == 0) {
                        $("#item" + i).addClass("active");
                        $("#slide" + i).addClass("active");
                    }
                })
            }
        ],
        error: [
            function (request, status) {
                alert(status);
            }
        ]
    });
}

function createContainerBars() {
    Highcharts.chart('containerBars', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Capturados Last 7 Days'
        },
        subtitle: {
            text: 'Chart with the profemons you\'ve caught in the last 7 days'
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Profemons (quantity)'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: 'Capturados: <b>{point.y:.1f}</b>'
        },
        series: [{
            name: 'Capturados',
            data: [],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                align: 'right',
                format: '{point.y:.1f}', // one decimal
                y: 10, // 10 pixels down from the top
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        }]
    });
}

function fillContainerBars(userIdCurrentlyLoggedIn) {
    $.ajax({
        type: "GET",
        url: "http://147.83.7.207:8080/pokeetac/user/capturados/successfulByDay/" + userIdCurrentlyLoggedIn,
        contentType: "application/json",
        success: [
            function (data) {
                $.each(data, function (i, item) {
                    $("#containerBars").highcharts().addSeries({
                        data: [[data[i].date, data[i].capturados]]
                    });
                })
            }
        ],
        error: [
            function (request, status) {
                alert(status);
            }
        ]
    });
}

function createContainerPie() {
    Highcharts.chart('containerPie', {
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: 'Successful Capturados Percentage'
        },
        tooltip: {
            pointFormat: '<b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Successful Capturados Percentage',
            data: []
        }]
    });
}

function fillContainerPie(userIdCurrentlyLoggedIn) {
    $.ajax({
        type: "GET",
        url: "http://147.83.7.207:8080/pokeetac/user/capturados/successfulPercentage/" + userIdCurrentlyLoggedIn,
        contentType: "application/json",
        success: [
            function (data) {
                var successfulCapturadosPercentage = parseFloat(data.successfulPercentage);
                $("#containerPie").highcharts().addSeries({
                    data: [["Successful", successfulCapturadosPercentage],
                        ["Unsuccessful", 100 - successfulCapturadosPercentage]]
                });
            }
        ],
        error: [
            function (request, status) {
                alert(status);
            }
        ]
    });
}
