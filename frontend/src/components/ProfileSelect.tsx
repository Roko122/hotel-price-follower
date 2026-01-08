import { Select, SelectContent, SelectItem, SelectSeparator, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Fragment } from 'react';

import type { JSX } from 'react';
import type { SearchProfile } from '@/types/SearchProfile.js';

type ProfileSelectProps = {
  profiles: SearchProfile[];
  selectedProfile: string | undefined;
  setSelectedProfile: (selectedHProfile: string | undefined) => void;
};

function ProfileSelect(props: ProfileSelectProps): JSX.Element {
  const { profiles, selectedProfile, setSelectedProfile } = props;

  const weekText = (weeks: number): string => {
    if (weeks === 1) {
      return `${weeks} viikko`;
    } else {
      return `${weeks} viikkoa`;
    }
  };

  const adultText = (adults: number): string => {
    if (adults === 1) {
      return `${adults} aikuinen`;
    } else {
      return `${adults} aikuista`;
    }
  };

  const childrenText = (children: number): string => {
    if (children === 1) {
      return `${children} lapsi`;
    } else {
      return `${children} lasta`;
    }
  };

  const profileText = (profile: SearchProfile): string => {
    if (profile.children === 0) {
      return `${adultText(profile.adults)}, ${weekText(profile.durationWeeks)}`;
    } else {
      return `${adultText(profile.adults)}, ${childrenText(profile.children)}, ${weekText(profile.durationWeeks)}`;
    }
  };

  return (
    <Select value={selectedProfile} onValueChange={setSelectedProfile}>
      <SelectTrigger className="w-full font-bold">
        <SelectValue placeholder="Valitse profiili" />
      </SelectTrigger>
      <SelectContent position="popper" className="w-full">
        {profiles.map((profile, index) => (
          <Fragment key={profile.id}>
            <SelectItem value={profile.id.toString()}>{profileText(profile)}</SelectItem>
            {index < profiles.length - 1 && <SelectSeparator />}
          </Fragment>
        ))}
      </SelectContent>
    </Select>
  );
}

export default ProfileSelect;
