function makeLink(msg, link, len = 15) {
    let str = String(msg);
    // return '<a class="link" href="' + link + '">' + (str.length > len ? (str.slice(0, len) + '...') : str) + '</a>';
    return (str.length > len ? (str.slice(0, len) + '...') : str);
}

function getChart(map,i){
    var xlist=[];
    var ylist=[];
    var temp=Object.values(map);
    for(var j=0; j<10; j++){
        xlist.push(temp[j].name);
        ylist.push(temp[j].count);
    }

    console.log(xlist);
    console.log(ylist);

    var chart = echarts.init(document.getElementById('trend' + i ));
    chart.setOption({
        tooltip: {
            trigger: 'axis',//移到柱状图上触发详情
            axisPointer: {
                type: 'shadow'//阴影效果
            }
        },
        xAxis: {
            type: 'category',
            name: '热点',
            data: xlist,
            axisLabel: {
                show:true
            },
            axisTick: {
                show: true
            },
            axisLine: {
                show: false
            }
        },
        yAxis: {
            show: false,
            type: 'value',
            name: '热度',
            minInterval: 1,
            axisTick: {
                show: false//隐藏y坐标轴刻度
            },
            axisLine: {
                show: true
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#9495ab',
                    fontSize:'12'
                }
            },
            //网格样式
            splitLine: {
                show: true,
                lineStyle:{
                    color: '#e4e4e4',
                    width: 1,
                    type: 'solid'
                }
            },
        },
        series: [{
            name: '热度',
            type: 'bar',
            legendHoverLink: true,
            data: ylist
        }]
    });
}

$(document).ready(function () {

    function getTrend() {
        getRequest(
            '/trend',
            function (res) {
                console.log(res.content.trendForms);
                var trendForms = res.content.trendForms;
                var forms = [];
                for (let f in trendForms) {
                    forms.push({
                        name: f,
                        value: trendForms[f]
                    })
                }
                var info = "";
                for(var i=5; i>=0; i--){
                    form=forms[i];
                    console.log(form.name);
                    info +=
                        "<tr> " +
                        "<td>" + form.name  + "</td>" +
                        "<td><div id='trend" + i + "' style='width: 600px;height:400px;'></div></td>" +
                        "</tr>";

                    $('#ranking').html(info);
                    document.getElementById("ranking").innerHTML = info;
                }
                for(var i=5;i>=0;i--){
                    form=forms[i];
                    getChart(form.value, i);
                }
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    }

    getTrend();
});