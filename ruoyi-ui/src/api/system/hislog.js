import request from '@/utils/request'

// 查询拦截日志列表
export function listHislog(query) {
  return request({
    url: '/system/hislog/list',
    method: 'get',
    params: query
  })
}

// 查询拦截日志详细
export function getHislog(id) {
  return request({
    url: '/system/hislog/' + id,
    method: 'get'
  })
}

// 新增拦截日志
export function addHislog(data) {
  return request({
    url: '/system/hislog',
    method: 'post',
    data: data
  })
}

// 修改拦截日志
export function updateHislog(data) {
  return request({
    url: '/system/hislog',
    method: 'put',
    data: data
  })
}

// 删除拦截日志
export function delHislog(id) {
  return request({
    url: '/system/hislog/' + id,
    method: 'delete'
  })
}

// 导出拦截日志
export function exportHislog(query) {
  return request({
    url: '/system/hislog/export',
    method: 'get',
    params: query
  })
}