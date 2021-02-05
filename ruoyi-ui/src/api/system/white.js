import request from '@/utils/request'

// 查询白名单列表
export function listWhite(query) {
  return request({
    url: '/system/white/list',
    method: 'get',
    params: query
  })
}

// 查询白名单详细
export function getWhite(id) {
  return request({
    url: '/system/white/' + id,
    method: 'get'
  })
}

// 新增白名单
export function addWhite(data) {
  return request({
    url: '/system/white',
    method: 'post',
    data: data
  })
}

// 修改白名单
export function updateWhite(data) {
  return request({
    url: '/system/white',
    method: 'put',
    data: data
  })
}

// 删除白名单
export function delWhite(id) {
  return request({
    url: '/system/white/' + id,
    method: 'delete'
  })
}

// 导出白名单
export function exportWhite(query) {
  return request({
    url: '/system/white/export',
    method: 'get',
    params: query
  })
}