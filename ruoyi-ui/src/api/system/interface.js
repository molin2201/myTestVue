import request from '@/utils/request'

// 查询接口配置列表
export function listInterface(query) {
  return request({
    url: '/system/interface/list',
    method: 'get',
    params: query
  })
}

// 查询接口配置详细
export function getInterface(id) {
  return request({
    url: '/system/interface/' + id,
    method: 'get'
  })
}

// 新增接口配置
export function addInterface(data) {
  return request({
    url: '/system/interface',
    method: 'post',
    data: data
  })
}

// 修改接口配置
export function updateInterface(data) {
  return request({
    url: '/system/interface',
    method: 'put',
    data: data
  })
}

// 删除接口配置
export function delInterface(id) {
  return request({
    url: '/system/interface/' + id,
    method: 'delete'
  })
}

// 导出接口配置
export function exportInterface(query) {
  return request({
    url: '/system/interface/export',
    method: 'get',
    params: query
  })
}