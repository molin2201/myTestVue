import request from '@/utils/request'

// 查询黑名单列表
export function listBlack(query) {
  return request({
    url: '/system/black/list',
    method: 'get',
    params: query
  })
}

// 查询黑名单详细
export function getBlack(id) {
  return request({
    url: '/system/black/' + id,
    method: 'get'
  })
}

// 新增黑名单
export function addBlack(data) {
  return request({
    url: '/system/black',
    method: 'post',
    data: data
  })
}

// 修改黑名单
export function updateBlack(data) {
  return request({
    url: '/system/black',
    method: 'put',
    data: data
  })
}

// 删除黑名单
export function delBlack(id) {
  return request({
    url: '/system/black/' + id,
    method: 'delete'
  })
}

// 导出黑名单
export function exportBlack(query) {
  return request({
    url: '/system/black/export',
    method: 'get',
    params: query
  })
}