import { AxiosHttp } from '@/http/AxiosHttp';
import { AxiosResponse } from 'axios';
import { Modules } from '../domain/Modules';
import { Category } from '../domain/Category';
import { Module } from '../domain/Module';
import { ModulesRepository } from '../domain/ModulesRepository';
import { ModuleProperty, ModulePropertyType } from '../domain/ModuleProperty';
import { ModuleToApply } from '../domain/ModuleToApply';
import { ModuleSlug } from '../domain/ModuleSlug';

export interface RestModules {
  categories: RestCategory[];
}

export interface RestCategory {
  name: string;
  modules: RestModule[];
}

export interface RestModule {
  slug: string;
  description: string;
  properties?: RestModuleProperties;
}

export interface RestModuleProperties {
  definitions: RestModuleProperty[];
}

export interface RestModuleProperty {
  type: ModulePropertyType;
  mandatory: boolean;
  key: string;
  description?: string;
  example?: string;
}

export interface RestModuleToApply {
  projectFolder: string;
  properties: {};
}

export class RestModulesRepository implements ModulesRepository {
  constructor(private axiosInstance: AxiosHttp) {}

  list(): Promise<Modules> {
    return this.axiosInstance.get<RestModules>('/api/modules').then(mapToModules);
  }

  apply(module: ModuleSlug, moduleToApply: ModuleToApply): Promise<void> {
    return this.axiosInstance
      .post<void, RestModuleToApply>(`/api/modules/${module}/apply-patch`, toRestModuleToApply(moduleToApply))
      .then(() => undefined);
  }
}

const mapToModules = (response: AxiosResponse<RestModules>): Modules => ({
  categories: response.data.categories.map(toCategory),
});

const toCategory = (restCategory: RestCategory): Category => ({
  name: restCategory.name,
  modules: restCategory.modules.map(toModule),
});

const toModule = (restModule: RestModule): Module => ({
  slug: restModule.slug,
  description: restModule.description,
  properties: toProperties(restModule.properties),
});

const toProperties = (restProperties: RestModuleProperties | undefined): ModuleProperty[] => {
  if (!restProperties) {
    return [];
  }

  return restProperties.definitions.map(toProperty);
};

const toProperty = (restProperty: RestModuleProperty): ModuleProperty => ({
  type: restProperty.type,
  mandatory: restProperty.mandatory,
  key: restProperty.key,
  description: restProperty.description,
  example: restProperty.example,
});

const toRestModuleToApply = (moduleToApply: ModuleToApply): RestModuleToApply => ({
  projectFolder: moduleToApply.projectFolder,
  properties: Object.fromEntries(moduleToApply.properties),
});
