import request from '@/utils/request'

// 查询城市号码列表
export function listCity(query) {
  return request({
    url: '/system/city/list',
    method: 'get',
    params: query
  })
}

// 查询城市号码详细
export function getCity(id) {
  return request({
    url: '/system/city/' + id,
    method: 'get'
  })
}

// 新增城市号码
export function addCity(data) {
  return request({
    url: '/system/city',
    method: 'post',
    data: data
  })
}

// 修改城市号码
export function updateCity(data) {
  return request({
    url: '/system/city',
    method: 'put',
    data: data
  })
}

// 删除城市号码
export function delCity(id) {
  return request({
    url: '/system/city/' + id,
    method: 'delete'
  })
}

// 导出城市号码
export function exportCity(query) {
  return request({
    url: '/system/city/export',
    method: 'get',
    params: query
  })
}