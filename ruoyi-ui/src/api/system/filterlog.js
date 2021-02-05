import request from '@/utils/request'

// 查询拦截日志列表
export function listLog(query) {
  return request({
    url: '/system/filterlog/list',
    method: 'get',
    params: query
  })
}

// 查询拦截日志详细
export function getLog(id) {
  return request({
    url: '/system/filterlog/' + id,
    method: 'get'
  })
}

// 新增拦截日志
export function addLog(data) {
  return request({
    url: '/system/filterlog',
    method: 'post',
    data: data
  })
}

// 修改拦截日志
export function updateLog(data) {
  return request({
    url: '/system/filterlog',
    method: 'put',
    data: data
  })
}

// 删除拦截日志
export function delLog(id) {
  return request({
    url: '/system/filterlog/' + id,
    method: 'delete'
  })
}

// 导出拦截日志
export function exportLog(query) {
  return request({
    url: '/system/filterlog/export',
    method: 'get',
    params: query
  })
}
