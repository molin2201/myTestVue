import request from '@/utils/request'

// 查询费率管理列表
export function listRate(query) {
  return request({
    url: '/system/rate/list',
    method: 'get',
    params: query
  })
}

// 查询费率管理详细
export function getRate(id) {
  return request({
    url: '/system/rate/' + id,
    method: 'get'
  })
}

// 新增费率管理
export function addRate(data) {
  return request({
    url: '/system/rate',
    method: 'post',
    data: data
  })
}

// 修改费率管理
export function updateRate(data) {
  return request({
    url: '/system/rate',
    method: 'put',
    data: data
  })
}

// 删除费率管理
export function delRate(id) {
  return request({
    url: '/system/rate/' + id,
    method: 'delete'
  })
}

// 导出费率管理
export function exportRate(query) {
  return request({
    url: '/system/rate/export',
    method: 'get',
    params: query
  })
}