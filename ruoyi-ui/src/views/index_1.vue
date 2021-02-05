<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
  import echarts from 'echarts'
  require('echarts/theme/macarons') // echarts theme
  import resize from '@/views/dashboard/mixins/resize'
  import 'echarts/map/js/china'
  import china from 'echarts/map/json/china.json'
  echarts.registerMap('china', china)

  function randomData() {
    return Math.round(Math.random()*500);
  }
  export default {
    mixins: [resize],
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '500px'
      },
      height: {
        type: String,
        default: '500px'
      }
    },
    data() {
      return {
        chart: null
      }
    },
    mounted() {
      this.$nextTick(() => {
        this.initChart()
      })
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      this.chart.dispose()
      this.chart = null
    },
    methods: {
      initChart() {
        this.chart = echarts.init(this.$el, 'macarons')
        this.chart.setOption({
          tooltip: {},
          legend: {
            orient: 'vertical',
            left: 'left',
            data:['']
          },
          visualMap: {
            min: 0,
            max: 1500,
            left: '10%',
            top: 'bottom',
            text: ['高','低'],
            calculable : true,
            color:['#b90c0b','#c3e2f4']
          },
          selectedMode: 'single',
          series : [
            {
              name: '中国地图',
              type: 'map',
              mapType: 'china',
              itemStyle: {
                normal:{
                  borderColor: 'rgba(0, 0, 0, 0.2)'
                },
                emphasis:{
                  shadowOffsetX: 0,
                  shadowOffsetY: 0,
                  shadowBlur: 20,
                  borderWidth: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
              showLegendSymbol: true,
              label: {
                normal: {
                  show: true
                },
                emphasis: {
                  show: true
                }
              },
              data:[
                {name: '北京',value: randomData() },
                {name: '天津',value: randomData() },
                {name: '上海',value: randomData() },
                {name: '重庆',value: randomData() },
                {name: '河北',value: randomData() },
                {name: '河南',value: randomData() },
                {name: '云南',value: randomData() },
                {name: '辽宁',value: randomData() },
                {name: '黑龙江',value: randomData() },
                {name: '湖南',value: randomData() },
                {name: '安徽',value: randomData() },
                {name: '山东',value: randomData() },
                {name: '新疆',value: randomData() },
                {name: '江苏',value: randomData() },
                {name: '浙江',value: randomData() },
                {name: '江西',value: randomData() },
                {name: '湖北',value: randomData() },
                {name: '广西',value: randomData() }
              ]
          }]
        })
      }
    }
  }
</script>
