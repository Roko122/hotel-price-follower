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

  const profileText = (profile: SearchProfile): string => {
    if (profile.children === 0) {
      return `${profile.adults} aikuista, ${weekText(profile.durationWeeks)}`;
    } else {
      return `${profile.adults} aikuista, ${profile.children} lasta, ${profile.durationWeeks} viikkoa`;
    }
  };

  return (
    <Select value={selectedProfile} onValueChange={setSelectedProfile}>
      <SelectTrigger className="w-full">
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
